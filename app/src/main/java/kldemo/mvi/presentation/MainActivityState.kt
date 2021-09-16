package kldemo.mvi.presentation

sealed class MainActivityState {
    data class Init(val numberOfClick: Int) : MainActivityState()
    object Awaiting : MainActivityState()
}