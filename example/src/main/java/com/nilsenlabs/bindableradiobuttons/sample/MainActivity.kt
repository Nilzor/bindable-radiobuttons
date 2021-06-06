package com.nilsenlabs.bindableradiobuttons.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.nilsenlabs.bindableradiobuttons.databinding.ActivityMainBinding
import com.nilsenlabs.bindableradiobuttons.databinding.MyCustomRadiobutton2Binding
import com.nilsenlabs.bindableradiobuttons.databinding.MyCustomRadiobuttonBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = MainActivityViewModel()
        setContentView(binding.root)

        setupCustomRadioGroup(binding, binding.viewModel!!)
    }

    private fun setupCustomRadioGroup(binding: ActivityMainBinding, viewModel: MainActivityViewModel) {
        for (item in viewModel.customRadioButtons) {
            val buttonBinding = MyCustomRadiobuttonBinding.inflate(layoutInflater)
            buttonBinding.root.id = ViewCompat.generateViewId()
            buttonBinding.viewModel = item
            binding.customRadiogroup.addView(buttonBinding.root)
            Log.d("zzz", "Inflated view with id ${buttonBinding.root.id}")
        }
    }
}