package me.singleneuron.qn_kernel.tlb

import me.kyuubiran.hook.RemovePlayTogether
import me.nextalone.hook.ForcedSendOriginalPhoto
import me.nextalone.hook.HideProfileBubble
import me.nextalone.hook.HideTotalNumber
import me.singleneuron.hook.ChangeDrawerWidth
import me.singleneuron.hook.ForceSystemCamera
import me.singleneuron.hook.ForceSystemFile
import me.singleneuron.hook.NewRoundHead
import me.singleneuron.util.QQVersion
import nil.nadph.qnotified.hook.VasProfileAntiCrash
import nil.nadph.qnotified.util.Utils

object ConfigTable {

    private val configs: Map<String?, Map<Long, Any>> = mapOf(

            //去com.tencent.mobileqq.activity.recent.DrawerFrame类里面找一个奇怪的只有一行以一个ID从Resources获取DimensionPixelSize的方法（大概率在最末尾），然后把ID填过来
            //一般是R.dimen.akx
            ChangeDrawerWidth::class.simpleName to mapOf(
                    QQVersion.QQ_8_4_1 to 0x7f090834,
                    QQVersion.QQ_8_4_5 to 0x7f090841,
                    QQVersion.QQ_8_4_8 to 0x7f090882,
                    QQVersion.QQ_8_4_10 to 0x7f90886,
                    QQVersion.QQ_8_4_17 to 0x7f090896,
                    QQVersion.QQ_8_4_18 to 0x7f090896,
                    QQVersion.QQ_8_5_0 to 0x7f090921,
                    QQVersion.QQ_8_5_5 to 0x7f0908dc,
            ),

            //特征字符串："FaceManager"
            NewRoundHead::class.simpleName to mapOf(
                    QQVersion.QQ_8_3_6 to "beft",
                    QQVersion.QQ_8_3_9 to "bfsw",
                    QQVersion.QQ_8_4_1 to "aocs",
                    QQVersion.QQ_8_4_5 to "aope",
                    QQVersion.QQ_8_4_8 to "anho",
                    QQVersion.QQ_8_4_10 to "aoke",
                    QQVersion.QQ_8_4_17 to "aowc",
                    QQVersion.QQ_8_4_18 to "aowc",
                    QQVersion.QQ_8_5_0 to "com.tencent.mobileqq.avatar.utils.AvatarUtil",
                    QQVersion.QQ_8_5_5 to "com.tencent.mobileqq.avatar.utils.AvatarUtil",
            ),

            //特征字符串："CaptureUtil"
            ForceSystemCamera::class.simpleName to mapOf(
                    QQVersion.QQ_8_3_6 to "aypd",
                    QQVersion.QQ_8_3_9 to "babg",
                    QQVersion.QQ_8_4_1 to "bann",
                    QQVersion.QQ_8_4_5 to "bbgg",
                    QQVersion.QQ_8_4_8 to "babd",
                    QQVersion.QQ_8_4_10 to "bbhm",
                    QQVersion.QQ_8_4_17 to "bcmd",
                    QQVersion.QQ_8_4_18 to "bcmd",
                    QQVersion.QQ_8_5_0 to "com/tencent/mobileqq/richmedia/capture/util/CaptureUtil",
                    QQVersion.QQ_8_5_5 to "com/tencent/mobileqq/richmedia/capture/util/CaptureUtil"
            ),

            //特征字符串:"SmartDeviceProxyMgr create"
            ForceSystemFile::class.simpleName to mapOf(
                    QQVersion.QQ_8_3_6 to "zyr",
                    QQVersion.QQ_8_3_9 to "aaxe",
                    QQVersion.QQ_8_4_1 to "abqn",
                    QQVersion.QQ_8_4_5 to "abur",
                    QQVersion.QQ_8_4_8 to "aara",
                    QQVersion.QQ_8_4_10 to "abgm",
                    QQVersion.QQ_8_4_17 to "abpa",
                    QQVersion.QQ_8_4_18 to "abpa",
                    QQVersion.QQ_8_5_0 to "com/tencent/device/devicemgr/SmartDeviceProxyMgr",
                    QQVersion.QQ_8_5_5 to "com/tencent/device/devicemgr/SmartDeviceProxyMgr",
            ),

            // 字符串关键字 updateProfileBubbleMsgView
            HideProfileBubble::class.simpleName to mapOf(
                    QQVersion.QQ_8_3_9 to "S",
                    QQVersion.QQ_8_4_1 to "V",
                    QQVersion.QQ_8_4_5 to "V",
                    QQVersion.QQ_8_4_8 to "U",
                    QQVersion.QQ_8_4_10 to "Y",
                    QQVersion.QQ_8_4_17 to "Y",
                    QQVersion.QQ_8_4_18 to "Y",
                    QQVersion.QQ_8_5_0 to "Z",
                    QQVersion.QQ_8_5_5 to "Z"
            ),

            VasProfileAntiCrash::class.java.simpleName to mapOf(
                    QQVersion.QQ_8_4_1 to "azfl",
                    QQVersion.QQ_8_4_5 to "azxy",
                    QQVersion.QQ_8_4_8 to "aymn"
            ),

            //一般是R.id.h1y
            ForcedSendOriginalPhoto::class.java.simpleName to mapOf(
                    QQVersion.QQ_8_4_1 to 0x7f0a3262,
                    QQVersion.QQ_8_4_5 to 0x7f0a32eb,
                    QQVersion.QQ_8_4_8 to 0x7f0a3200,
                    QQVersion.QQ_8_4_10 to 0x7f0a32f0,
                    QQVersion.QQ_8_4_17 to 0x7f0a33cc,
                    QQVersion.QQ_8_4_18 to 0x7f0a33cc,
                    QQVersion.QQ_8_5_0 to 0x7f0a347a,
                    QQVersion.QQ_8_5_5 to 0x7f0a3469
            ),

            //com.tencent.mobileqq.activity.aio.core.TroopChatPie中一般是包含R.id.blz的
            HideTotalNumber::class.java.simpleName to mapOf(
                    QQVersion.QQ_8_4_1 to "bE",
                    QQVersion.QQ_8_4_5 to "bE",
                    QQVersion.QQ_8_4_8 to "r",
                    QQVersion.QQ_8_4_10 to "t",
                    QQVersion.QQ_8_4_17 to "t",
                    QQVersion.QQ_8_4_18 to "t",
                    QQVersion.QQ_8_5_0 to "s",
                    QQVersion.QQ_8_5_5 to "bz"
            ),

            RemovePlayTogether::class.java.simpleName to mapOf(
                    QQVersion.QQ_8_4_8 to "agpr",
                    QQVersion.QQ_8_4_10 to "aghe",
                    QQVersion.QQ_8_4_17 to "agpr",
                    QQVersion.QQ_8_4_18 to "agpr",
                    QQVersion.QQ_8_5_0 to "com/tencent/mobileqq/activity/aio/helper/ClockInEntryHelper",
                    QQVersion.QQ_8_5_5 to "com/tencent/mobileqq/activity/aio/helper/ClockInEntryHelper",
            ),
    )

    private val cacheMap: Map<String?, Any?> by lazy {
        val map: HashMap<String?, Any?> = HashMap()
        val versionCode = Utils.getHostVersionCode()
        for (pair in configs) {
            if (pair.value.containsKey(versionCode)) {
                map[pair.key] = pair.value[versionCode]
            }
        }
        map
    }

    fun <T> getConfig(className: String?): T {
        val config = cacheMap[className]
        return config as T
                ?: throw RuntimeException("$className :Unsupported QQ Version")
    }

}
