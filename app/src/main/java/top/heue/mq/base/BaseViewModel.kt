package top.heue.mq.base

import android.view.Menu
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent
import java.lang.reflect.Method

open class BaseViewModel : ViewModel(), KoinComponent {
    /**
     * 解决不显示menu icon的问题
     * @param menu
     * @param flag
     */
    fun setIconsVisible(menu: Menu?, flag: Boolean) {
        //判断menu是否为空
        if (menu != null) {
            try {
                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                val method: Method = menu.javaClass
                    .getDeclaredMethod("setOptionalIconsVisible", java.lang.Boolean.TYPE)
                //暴力访问该方法
                method.isAccessible = true
                //调用该方法显示icon
                method.invoke(menu, flag)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}