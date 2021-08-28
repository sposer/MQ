package top.heue.mq.service

import kotlinx.coroutines.delay
import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.network.LoginFailedException
import top.heue.mq.base.BaseService
import top.heue.mq.helper.CH
import top.heue.mq.util.BotLogger
import top.heue.mq.util.DeviceInfo
import top.heue.mq.util.NetLogger
import top.heue.mq.util.T
import top.heue.utils.log.L
import java.io.File

class MQService : BaseService<MQService>() {
    private lateinit var bot: Bot

    fun initBot(uid: Long, pwd: String) {
        var result = false
        CH {
            handler { _, throwable ->
                L.e("登录失败", throwable)
            }
            onIO {
                bot = BotFactory.newBot(uid, pwd) {
                    //workingDir = File("/storage/emulated/0/ADN/Bot")
                    loadDeviceInfoJson(DeviceInfo.info)

                    networkLoggerSupplier = { NetLogger() }
                    botLoggerSupplier = { BotLogger() }
                    //noNetworkLog()
                    //noBotLog()
                }
                bot.eventChannel.subscribeAlways<MessageEvent> {
                    L.d("Rec", message)
                }
                try {
                    bot.login()
                } catch (e: LoginFailedException) {
                    L.d("Login Failed", e)
                    T.show(e.message)
                }
            }
        }
    }
}