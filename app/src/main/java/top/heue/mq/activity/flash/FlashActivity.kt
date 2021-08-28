package top.heue.mq.activity.flash

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.KeyEvent
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import top.heue.mq.BR
import top.heue.mq.R
import top.heue.mq.activity.contain.ContainActivity
import top.heue.mq.activity.main.MainActivity
import top.heue.mq.base.BaseActivity
import top.heue.mq.base.BaseService
import top.heue.mq.databinding.ActivityFlashBinding
import top.heue.mq.fragment.login.LoginFragment
import top.heue.mq.service.AppService
import top.heue.mq.util.FragmentContainer
import top.heue.utils.log.L
import top.heue.utils.log.LogUtils.c

class FlashActivity : BaseActivity() {

    private val flashModel: FlashModel by viewModel()
    lateinit var binding: ActivityFlashBinding
    lateinit var service: AppService
    private val con = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if (service != null) {
                this@FlashActivity.service = getService(service)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            L.c("Flash")
        }

    }

    override fun onCreate() {
        super.onCreate()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flash)
        binding.setVariable(BR.data, flashModel)
        binding.lifecycleOwner = this

        val intent = Intent(this, AppService::class.java)
        bindService(intent, con, BIND_AUTO_CREATE)
    }

    private val fragmentContainer: FragmentContainer by inject()
    private val loginFragment: LoginFragment by inject()
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) return
        val i = if (service.hasLoginAccount)
            Intent(this, MainActivity::class.java)
        else {
            fragmentContainer.fragment = loginFragment
            Intent(this, ContainActivity::class.java)
        }
        startActivity(i)
        finish()
    }

    //固定事件监听
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                finish()
                false
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}