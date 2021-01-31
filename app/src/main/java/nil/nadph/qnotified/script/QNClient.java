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
package nil.nadph.qnotified.script;

import me.singleneuron.qn_kernel.data.HostInformationProviderKt;
import nil.nadph.qnotified.bridge.ChatActivityFacade;
import nil.nadph.qnotified.bridge.SessionInfoImpl;
import nil.nadph.qnotified.util.LicenseStatus;
import nil.nadph.qnotified.util.Utils;
import static nil.nadph.qnotified.util.ReflexUtil.invoke_virtual;
import static nil.nadph.qnotified.util.Utils.log;


public class QNClient {
    /*
    QNClient.send(好友/群号码,"wdnmd当场裂开来");// 发送文字消息
	QNClient.sendImg(好友/群号码,"url或者文件");// 发送图片
	QNClient.sendRecord(好友/群号码,"url或者文件");// 发送语音
	QNClient.sendCard(好友/群号码,"json或者xml代码");// 发送卡片消息(只有高级白名单才可以)
	QNClient.kick(群号码,成员号码);// 从一个群踢出一个人
	QNClient.mute(群号码,成员号码,禁言时间[秒]);// 在某个群禁言一个人
	QNClient.unmute(群号码,成员号码);// 在某个群解除禁言一个人
	QNClient.muteAll(群号码);// 开启一个群的全群禁言
	QNClient.unmuteAll(群号码);// 关闭一个群的全群禁言
     */

    /**
     * 发送一条文字消息
     *
     * @param uin     要发送的 群/好友
     * @param content 要发送的内容
     * @param type    类型，当发送给好友为0.否则为1
     */
    public static void send(String uin, String content, int type) {
        // to do
        ChatActivityFacade.sendMessage(
                Utils.getQQAppInterface(), HostInformationProviderKt.getHostInformationProvider().getApplicationContext(), SessionInfoImpl.createSessionInfo(uin, type), content
        );
    }

    /**
     * 发送一条图片消息
     *
     * @param uin     要发送的 群/好友
     * @param content 要发送的图片，使用 url/文件地址
     */
    public static void sendImg(long uin, String content) {
        if (content.startsWith("http") || content.startsWith("https")) {
            // 为url
        } else {
            // 为文件
        }
        // to do
    }

    /**
     * 发送一条语音消息
     *
     * @param uin     要发送的 群/好友
     * @param content 要发送的语音，使用 url/文件地址
     */
    public static void sendRecord(long uin, String content) {
        if (content.startsWith("http") || content.startsWith("https")) {
            // 为url
        } else {
            // 为文件
        }
        // to do
    }

    /**
     * 发送卡片消息
     * NOTICE：需要高级白名单
     *
     * @param uin     要发送的 群/好友
     * @param content xml/json
     */
    public static void sendCard(long uin, String content) {
        if (!LicenseStatus.isAsserted()) return;
        // to do
    }

    /**
     * 踢出一个人
     *
     * @param groupUin 群id
     * @param uin      成员id
     */
    public static void kick(long groupUin, long uin) {
        // to do
    }

    /**
     * 禁言一个人
     *
     * @param groupUin 群id
     * @param uin      成员id
     * @param time     时间
     */
    public static void mute(long groupUin, long uin, long time) {
        try {
            // TODO check is current user admin
            Object manager = Utils.getManager(48);
            invoke_virtual(manager, "a", String.valueOf(groupUin), String.valueOf(uin), time, String.class, String.class, long.class);
        } catch (Throwable e) {
            log(e);
        }
    }

    /**
     * 解除禁言一个人
     *
     * @param groupUin 群id
     * @param uin      成员id
     */
    public static void unmute(long groupUin, long uin) {
        try {
            // TODO check is current user admin
            Object manager = Utils.getManager(48);
            invoke_virtual(manager, "a", String.valueOf(groupUin), String.valueOf(uin), 0L, String.class, String.class, long.class);
        } catch (Throwable e) {
            log(e);
        }
    }

    /**
     * 开启全体禁言
     *
     * @param groupUin 群id
     */
    public static void muteAll(long groupUin) {
        try {
            // TODO check is current user admin
            Object manager = Utils.getManager(48);
            invoke_virtual(manager, "a", String.valueOf(groupUin), 268435455L, String.class, long.class);
        } catch (Throwable e) {
            log(e);
        }
    }

    /**
     * 关闭全体禁言
     *
     * @param groupUin 群id
     */
    public static void unmuteAll(long groupUin) {
        try {
            // TODO check is current user admin
            Object manager = Utils.getManager(48);
            invoke_virtual(manager, "a", String.valueOf(groupUin), 0L, String.class, long.class);
        } catch (Throwable e) {
            log(e);
        }
    }
}
