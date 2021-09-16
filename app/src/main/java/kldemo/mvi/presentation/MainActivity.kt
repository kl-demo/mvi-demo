package kldemo.mvi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kldemo.mvi.R
import kldemo.mvi.databinding.ActivityMainBinding
import kldemo.mvi.mvi.StateObserver

class MainActivity : AppCompatActivity(), StateObserver {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = MainActivityViewModel(applicationContext)
        viewModel.addObserver(this)
        viewModel.onAction(MainActivityAction.OnCreate)

        binding.incrementBtn.setOnClickListener {
            viewModel.onAction(MainActivityAction.OnIncrementButtonClick)
        }
    }

    override fun onStateChanged() {
        when (val state = viewModel.getState()) {
            is MainActivityState.Awaiting -> {
                binding.incrementContainer.visibility = View.GONE
                binding.awaitingContainer.visibility = View.VISIBLE
            }
            is MainActivityState.Init -> {
                binding.incrementText.text = getString(R.string.increment_text, state.numberOfClick)
                binding.awaitingContainer.visibility = View.GONE
                binding.incrementContainer.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.deleteObservers()
    }
}
