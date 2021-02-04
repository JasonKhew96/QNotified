/* QNotified - An Xposed module for QQ/TIM
 * Copyright (C) 2019-2021 xenonhydride@gmail.com
 * https://github.com/ferredoxin/QNotified
 *
 * This software is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see
 * <https://www.gnu.org/licenses/>.
 */
package nil.nadph.qnotified.hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import me.singleneuron.data.MsgRecordData;
import nil.nadph.qnotified.SyncUtils;
import nil.nadph.qnotified.script.QNScriptEventBus;
import nil.nadph.qnotified.script.QNScriptManager;
import nil.nadph.qnotified.script.params.ParamFactory;
import nil.nadph.qnotified.util.LicenseStatus;

import static nil.nadph.qnotified.util.Initiator.load;
import static nil.nadph.qnotified.util.Utils.log;

public class ScriptEventHook extends CommonDelayableHook {
    private static final ScriptEventHook self = new ScriptEventHook();

    private ScriptEventHook() {
        super("__NOT_USED__", SyncUtils.PROC_MAIN | SyncUtils.PROC_MSF);
    }

    public static ScriptEventHook get() {
        return self;
    }

    @Override
    public boolean initOnce() {
        QNScriptManager.init();
        try {
            Class<?> messageHandlerUtils = load("com.tencent.mobileqq.app.MessageHandlerUtils");
            if (messageHandlerUtils == null) {
                // QQ play 8.2.10
                messageHandlerUtils = load("ajys");
            }
            XposedHelpers.findAndHookMethod(messageHandlerUtils,
                "a",
                load("com.tencent.mobileqq.app.QQAppInterface"),
                load("com.tencent.mobileqq.data.MessageRecord"),
                boolean.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        if (LicenseStatus.sDisableCommonHooks) return;

                        MsgRecordData msgRecordData = new MsgRecordData(param.args[1]);
                        String uin = msgRecordData.getFriendUin();
                        String msg = msgRecordData.getMsg();
                        switch (msgRecordData.getMsgType()) {
                            case MsgRecordData.MSG_TYPE_TEXT:
                            case MsgRecordData.MSG_TYPE_REPLY_TEXT:
                                if (msgRecordData.isTroop() != 1) {
                                    QNScriptEventBus.broadcastFriendMessage(ParamFactory.friendMessage()
                                        .setContent(msg)
                                        .setUin(uin)
                                        .setTime(msgRecordData.getTime())
                                        .create());
                                } else {
                                    QNScriptEventBus.broadcastGroupMessage(ParamFactory.groupMessage()
                                        .setContent(msg)
                                        .setSenderUin(msgRecordData.getSenderUin())
                                        .setUin(uin)
                                        .setTime(msgRecordData.getTime())
                                        .create());
                                }
                                break;
                            default:
                                // TODO support other message type
                        }
                    }
                });
            return true;
        } catch (Throwable e) {
            log(e);
            return false;
        }
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {
        //do nothing
    }
}
