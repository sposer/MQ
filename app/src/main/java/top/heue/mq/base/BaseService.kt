package top.heue.mq.base

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import top.heue.utils.log.L
import top.heue.utils.log.LogUtils.c

open class BaseService<S: BaseService<S>>: Service() {
    private val className = this.javaClass.simpleName

    private lateinit var binder: MainBinder<S>

    class MainBinder<S>(val service: S) : Binder()

    override fun onCreate() {
        super.onCreate()
        binder = MainBinder(cast())
    }

    override fun onBind(intent: Intent?): IBinder {
        L.c(className)
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        L.c(className)
        return super.onUnbind(intent)
    }

    @Suppress("UNCHECKED_CAST")
    fun cast(): S {
        return this as S
    }
}