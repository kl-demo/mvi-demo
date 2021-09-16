package kldemo.mvi.mvi

interface ActionListener<T> {
    fun onAction(action: T)
}