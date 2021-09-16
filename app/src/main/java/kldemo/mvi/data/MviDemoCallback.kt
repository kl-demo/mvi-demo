package kldemo.mvi.data

interface MviDemoCallback<T> {
    fun onResult(result: T)
}