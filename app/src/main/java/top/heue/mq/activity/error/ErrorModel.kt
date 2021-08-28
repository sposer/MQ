package top.heue.mq.activity.error

import android.content.Intent
import top.heue.mq.activity.flash.FlashActivity
import top.heue.mq.base.BaseViewModel
import top.heue.utils.log.L
import top.heue.utils.log.LogUtils.clearAllLog

class ErrorModel : BaseViewModel() {
    var errorMsg: String? = null
    fun getMsg(intent: Intent) {
        errorMsg = intent.getStringExtra(ErrorActivity.TAG)
    }

    //restartApp
    fun restart(activity: ErrorActivity) {
        L.clearAllLog()
        val i = Intent(activity, FlashActivity::class.java)
        activity.startActivity(i)
        activity.finish()
    }
}