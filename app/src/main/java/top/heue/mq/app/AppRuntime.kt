package top.heue.mq.app

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level
import top.heue.mq.util.T

class AppRuntime : Application() {
    private val appModel = AppModel()

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.DEBUG)
            androidContext(baseContext)
            modules(appModel.module)
        }
        appModel.initToast(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        appModel.init(baseContext)
    }
}