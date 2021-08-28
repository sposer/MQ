package top.heue.mq.activity.main

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import top.heue.mq.base.BaseViewModel
import top.heue.mq.util.T

class MainModel: BaseViewModel() {

    private var exit = false
    fun onKeyBack(activity: MainActivity) {
        if (!exit) {
            exit = true
            T.show("再操作一次退出")
            Thread {
                Thread.sleep(1000)
                exit = false
            }.start()
        } else {
            activity.finish()
        }
    }

    //DrawerView 点击穿透解决
    fun drawerListen(drawerLayout: DrawerLayout) {
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerOpened(drawerView: View) {
                drawerView.isClickable = true
            }

            override fun onDrawerClosed(drawerView: View) {
                drawerView.isClickable = false
            }

            override fun onDrawerStateChanged(newState: Int) {

            }
        })
    }
}