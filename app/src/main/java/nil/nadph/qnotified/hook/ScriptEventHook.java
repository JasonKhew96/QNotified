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
import nil.nadph.qnotified.SyncUtils;
import nil.nadph.qnotified.script.QNScriptEventBus;
import nil.nadph.qnotified.script.QNScriptManager;
import nil.nadph.qnotified.script.params.ParamFactory;
import nil.nadph.qnotified.util.LicenseStatus;

import static nil.nadph.qnotified.util.Initiator.load;
import static nil.nadph.qnotified.util.ReflexUtil.iget_object_or_null;
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
                        // -1000 text
                        // -2000 photo
                        // -2007 sticker
                        // -1049 replyText
                        // -2022 video
                        // -2002 audio
                        int msgType = (Integer) iget_object_or_null(param.args[1], "msgtype");
                        int isTroop = (Integer) iget_object_or_null(param.args[1], "istroop");
                        String uin = (String) iget_object_or_null(param.args[1], "frienduin");
                        String msg = (String) iget_object_or_null(param.args[1], "msg");
                        switch (msgType) {
                            case -1000:
                                if (isTroop != 1) {
                                    QNScriptEventBus.broadcastFriendMessage(ParamFactory.friendMessage()
                                        .setContent(msg)
                                        .setUin(uin)
                                        .create());
                                } else {
                                    String senderUin = (String) iget_object_or_null(param.args[1], "senderuin");
                                    QNScriptEventBus.broadcastGroupMessage(ParamFactory.groupMessage()
                                        .setContent(msg)
                                        .setSenderUin(senderUin)
                                        .setUin(uin)
                                        .create());
                                }
                                break;
                            case -2000:
                            case -2007:
                            case -1049:
                            case -2022:
                            case -2002:
                            default:
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
