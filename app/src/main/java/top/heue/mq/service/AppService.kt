package top.heue.mq.service

import top.heue.mq.base.BaseService

class AppService: BaseService<AppService>() {
    val loginAccountList: MutableList<MQService> = mutableListOf()
    val hasLoginAccount: Boolean get() = loginAccountList.isNotEmpty()
}