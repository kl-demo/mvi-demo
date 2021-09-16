package kldemo.mvi.mvi

interface State<T> {
    fun getState(): T
    fun addObserver(stateObserver: StateObserver)
    fun deleteObserver(stateObserver: StateObserver)
    fun deleteObservers()
    fun hasObservers(): Boolean
}