package kldemo.mvi.presentation

sealed class MainActivityAction {
    object OnCreate : MainActivityAction()
    object OnIncrementButtonClick : MainActivityAction()
}
