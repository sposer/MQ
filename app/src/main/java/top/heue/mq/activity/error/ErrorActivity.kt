package top.heue.mq.activity.error

import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import top.heue.mq.BR
import top.heue.mq.R
import top.heue.mq.base.BaseActivity
import top.heue.mq.databinding.ActivityErrorBinding

class ErrorActivity : BaseActivity() {
    companion object {
        const val TAG = "ERROR"
    }

    private val errorModel: ErrorModel by viewModel()
    lateinit var binding: ActivityErrorBinding

    override fun onCreate() {
        super.onCreate()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_error)
        binding.setVariable(BR.data, errorModel)
        binding.lifecycleOwner = this
        val toolbar: Toolbar = binding.root.findViewById(R.id.toolbar)
        //errorMsg
        errorModel.getMsg(intent)
        //back
        toolbar.title = "程序错误"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_error, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_send_log -> {
                false
            }
            R.id.menu_restart -> {
                errorModel.restart(this)
                false
            }
            android.R.id.home -> {
                errorModel.restart(this)
                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //固定事件监听
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                errorModel.restart(this)
                false
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

}