package top.heue.mq.base

import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleEventObserver
import top.heue.utils.log.L
import top.heue.utils.log.LogUtils.n
import top.heue.utils.log.LogUtils.s

open class BaseActivity : AppCompatActivity() {

    private val className = this.javaClass.simpleName

    protected open fun onCreate() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        L.n(className)
        super.onCreate(savedInstanceState)
        //生命周期日志
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            L.d(className, event.name)
        })
        onCreate()
        L.s(className)
    }

    //Service Cast
    @Suppress("UNCHECKED_CAST")
    fun <T> getService(service: IBinder): T {
        val binder = service as BaseService.MainBinder<*>
        return binder.service as T
    }

    override fun finish() {
        super.finish()
        //切换动画
        //overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        //切换动画
        //overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
    }
}