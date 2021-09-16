package kldemo.mvi.presentation

import android.content.Context
import kldemo.mvi.data.MviDemoCallback
import kldemo.mvi.data.NumberOfClickRepository
import kldemo.mvi.mvi.ActionListener
import kldemo.mvi.mvi.State
import kldemo.mvi.mvi.StateObserver
import kotlin.properties.Delegates

class MainActivityViewModel(private val context: Context) : State<MainActivityState>, ActionListener<MainActivityAction> {
    private val observers: MutableList<StateObserver> = emptyList<StateObserver>().toMutableList()
    private var _state: MainActivityState by Delegates.observable(MainActivityState.Awaiting) { _, _, _ ->
        observers.forEach { it.onStateChanged() }
    }

    override fun getState(): MainActivityState = _state

    override fun addObserver(stateObserver: StateObserver) {
        observers.add(stateObserver)
    }

    override fun deleteObserver(stateObserver: StateObserver) {
        observers.remove(stateObserver)
    }

    override fun deleteObservers() {
        observers.clear()
    }

    override fun hasObservers(): Boolean = observers.isNotEmpty()

    override fun onAction(action: MainActivityAction) {
        _state = MainActivityState.Awaiting
        when (action) {
            MainActivityAction.OnCreate -> {
                NumberOfClickRepository(context).getNumberOfClick(object : MviDemoCallback<Int> {
                    override fun onResult(result: Int) {
                        _state = MainActivityState.Init(result)
                    }
                })
            }
            MainActivityAction.OnIncrementButtonClick -> {
                NumberOfClickRepository(context).increment(object : MviDemoCallback<Int> {
                    override fun onResult(result: Int) {
                        _state = MainActivityState.Init(result)
                    }
                })
            }
        }
    }
}