package kldemo.mvi.data

import android.content.Context
import kotlinx.coroutines.*

class NumberOfClickRepository(private val context: Context) {
    fun getNumberOfClick(callback: MviDemoCallback<Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000) //simulate response delay
            val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            val numberOfClick = sharedPref.getInt(NUMBER_OF_CLICK_KEY, 0)
            withContext(Dispatchers.Main) {
                callback.onResult(numberOfClick)
            }
        }
    }

    fun increment(callback: MviDemoCallback<Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000) //simulate response delay
            val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            var numberOfClick = sharedPref.getInt(NUMBER_OF_CLICK_KEY, 0)
            numberOfClick++
            with(sharedPref.edit()) {
                putInt(NUMBER_OF_CLICK_KEY, numberOfClick)
                commit()
                withContext(Dispatchers.Main) {
                    callback.onResult(numberOfClick)
                }
            }
        }
    }

    companion object {
        private const val SHARED_PREF_NAME = "NumberOfClickRepository"
        private const val NUMBER_OF_CLICK_KEY = "numberOfClick"
    }
}