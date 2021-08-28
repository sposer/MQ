package top.heue.mq.helper

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineHelper {

    constructor()

    constructor(block: CoroutineHelper.() -> Unit) {
        block.invoke(this)
    }

    /**
     * Run On IO
     * @param func 函数体
     */
    fun onIO(func: func): Job {
        return execution(Dispatchers.IO, func, handler)
    }

    /**
     * Run On Main
     * @param func 函数体
     */
    fun onMain(func: func): Job {
        return execution(Dispatchers.Main, func, handler)
    }

    private var handler: handler? = null
    /**
     * Error Handler
     * @param handler 函数体
     */
    fun handler(handler: handler) {
        this.handler = handler
    }

    private fun execution(
        mode: CoroutineDispatcher,
        func: func,
        handler: handler? = null
    ): Job {
        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            handler?.invoke(coroutineContext, throwable)
        }
        return CoroutineScope(mode).launch(errorHandler) {
            func.invoke(this)
        }
    }
}

typealias func = suspend CoroutineScope.() -> Unit
typealias handler = (coroutineContext: CoroutineContext, throwable: Throwable) -> Unit
typealias CH = CoroutineHelper