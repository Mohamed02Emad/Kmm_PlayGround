package com.mo.kmp_hello_world.utils


import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)
class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}




fun Flow<*>.subscribe(
    onEach: (item: Any) -> Unit,
    onComplete: () -> Unit,
    onThrow: (error: Throwable) -> Unit
): Job = this.subscribe(Dispatchers.Main, onEach, onComplete, onThrow)

fun Flow<*>.subscribe(
    dispatcher: CoroutineDispatcher,
    onEach: (item: Any) -> Unit,
    onComplete: () -> Unit,
    onThrow: (error: Throwable) -> Unit
): Job =
    this.onEach { onEach(it as Any) }
        .catch { onThrow(it) }
        .onCompletion { onComplete() }
        .launchIn(CoroutineScope(Job() + dispatcher))