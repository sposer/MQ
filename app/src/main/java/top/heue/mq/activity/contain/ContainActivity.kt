package top.heue.mq.activity.contain

import android.content.Intent
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import top.heue.mq.R
import top.heue.mq.base.BaseActivity
import top.heue.mq.databinding.ActivityContainBinding
import top.heue.mq.util.FragmentContainer

class ContainActivity: BaseActivity() {

    private lateinit var binding: ActivityContainBinding
    private val containModel: ContainModel by viewModel()
    private val fragmentContainer: FragmentContainer by inject()
    private lateinit var tipText: TextView

    override fun onCreate() {
        super.onCreate()
        binding = ActivityContainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tipText = binding.tipContain

        viewInit()
    }

    private fun viewInit() {
        val fragment = fragmentContainer.fragment
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_contain, fragment)
                //.addToBackStack(null)
                .commitAllowingStateLoss()
        } else {
            tipText.visibility = View.VISIBLE
        }
    }
}