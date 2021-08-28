package top.heue.mq.activity.main

import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import top.heue.mq.R
import top.heue.mq.base.BaseActivity
import top.heue.mq.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainModel: MainModel by viewModel()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate() {
        super.onCreate()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.root.findViewById(R.id.toolbar)
        drawerLayout = binding.drawerMain
        val navView: BottomNavigationView = binding.navViewMain

        setSupportActionBar(toolbar)
        mainModel.drawerListen(drawerLayout)
        val navController = findNavController(R.id.nav_host_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_message, R.id.navigation_contact, R.id.navigation_expand),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    //固定事件监听
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                if (drawerLayout.isOpen) {
                    drawerLayout.close()
                    return false
                }
                mainModel.onKeyBack(this)
                false
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    //点击不滑出解决
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}