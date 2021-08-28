package top.heue.mq.util

import net.mamoe.mirai.utils.MiraiLogger
import net.mamoe.mirai.utils.SimpleLogger
import top.heue.utils.log.L

class NetLogger :
    SimpleLogger(MQLogger.TAG_NET, { priority: LogPriority, message: String?, e: Throwable? ->
        run {
            //val log = "[${priority.name}] ${message ?: e}"
            val log: String = "${message ?: e}"
            when (priority.name) {
                MQLogger.V -> {
                    L.v(MQLogger.TAG_NET, log)
                }
                MQLogger.D -> {
                    L.d(MQLogger.TAG_NET, log)
                }
                MQLogger.I -> {
                    L.i(MQLogger.TAG_NET, log)
                }
                MQLogger.W -> {
                    L.w(MQLogger.TAG_NET, log)
                }
                MQLogger.E -> {
                    e?.let { L.e(MQLogger.TAG_NET, it) }
                }
                MQLogger.A -> {
                    L.e(MQLogger.TAG_NET, log)
                }
            }
        }
    })

class BotLogger :
    SimpleLogger(MQLogger.TAG_BOT, { priority: LogPriority, message: String?, e: Throwable? ->
        run {
            //val log = "[${priority.name}] ${message ?: e}"
            val log: String = "${message ?: e}"
            when (priority.name) {
                MQLogger.V -> {
                    L.v(MQLogger.TAG_BOT, log)
                }
                MQLogger.D -> {
                    L.d(MQLogger.TAG_BOT, log)
                }
                MQLogger.I -> {
                    L.i(MQLogger.TAG_BOT, log)
                }
                MQLogger.W -> {
                    L.w(MQLogger.TAG_BOT, log)
                }
                MQLogger.E -> {
                    e?.let { L.e(MQLogger.TAG_BOT, it) }
                }
                MQLogger.A -> {
                    L.e(MQLogger.TAG_BOT, log)
                }
            }
        }
    })

object MQLogger {
    const val V = "VERBOSE"
    const val D = "DEBUG"
    const val I = "INFO"
    const val W = "WARN"
    const val E = "ERROR"
    const val A = "ASSERT"

    const val TAG_NET = "NET"
    const val TAG_BOT = "BOT"
}