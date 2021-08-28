package top.heue.mq.app

import android.app.Application
import android.content.Context
import android.content.Intent
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import top.heue.mq.activity.contain.ContainModel
import top.heue.mq.activity.error.ErrorActivity
import top.heue.mq.activity.error.ErrorModel
import top.heue.mq.activity.flash.FlashModel
import top.heue.mq.activity.main.MainModel
import top.heue.mq.bean.LoginBean
import top.heue.mq.fragment.contact.ContactModel
import top.heue.mq.fragment.expand.ExpandModel
import top.heue.mq.fragment.login.LoginFragment
import top.heue.mq.fragment.login.LoginModel
import top.heue.mq.fragment.message.MessageModel
import top.heue.mq.util.FragmentContainer
import top.heue.mq.util.T
import top.heue.utils.log.L
import top.heue.utils.log.LogUtils.getAllLog

class AppModel {

    //依赖声明
    val module = module {
        viewModel(this)
        fragment(this)
        factory(this)
        single(this)
    }

    private fun viewModel(module: Module) {
        module.run {
            viewModel { ErrorModel() }
            viewModel { FlashModel() }
            viewModel { MessageModel() }
            viewModel { ContactModel() }
            viewModel { ExpandModel() }
            viewModel { MainModel() }
            viewModel { ContainModel() }
            viewModel { LoginModel() }
        }
    }

    private fun fragment(module: Module) {
        module.run {
            fragment { LoginFragment() }
        }
    }

    private fun factory(module: Module) {
        module.run {
            factory { LoginBean() }
        }
    }

    private fun single(module: Module) {
        module.run {
            single { FragmentContainer() }
        }
    }

    //Toast初始化
    fun initToast(application: Application) {
        T.init(application)
        T.setInterceptor {
            L.d("Toast", it)
            false
        }
    }

    //崩溃处理
    fun init(context: Context) {
        L.catchError({ throwable ->
            L.e("主线程", throwable)
            onError(context)
        }, { _, throwable ->
            L.e("子线程", throwable)
            onError(context)
        })
    }

    private fun onError(context: Context) {
        val intent = Intent(context, ErrorActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(ErrorActivity.TAG, L.getAllLog().toString())
        context.startActivity(intent)
    }

}