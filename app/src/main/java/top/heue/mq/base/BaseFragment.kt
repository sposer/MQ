package top.heue.mq.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleEventObserver
import top.heue.utils.log.L
import top.heue.utils.log.LogUtils.n
import top.heue.utils.log.LogUtils.s

open class BaseFragment: Fragment() {
    private val className = this.javaClass.simpleName
    private val bundleFragmentsKey = "android:support:fragments";
    override fun onAttach(context: Context) {
        L.n(className)
        super.onAttach(context)
        //生命周期日志
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            L.d(className, event.name)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        L.s(className)
    }
}