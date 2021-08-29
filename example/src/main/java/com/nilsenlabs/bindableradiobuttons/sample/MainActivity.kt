package com.nilsenlabs.bindableradiobuttons.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nilsenlabs.bindableradiobuttons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = MainActivityViewModel()
        setContentView(binding.root)

        // setupCustomRadioGroup(binding, binding.viewModel!!)
    }
/* todo experimental,
    private fun setupCustomRadioGroup(binding: ActivityMainBinding, viewModel: MainActivityViewModel) {
        for (item in viewModel.customRadioButtons) {
            val buttonBinding = MyCustomRadiobuttonBinding.inflate(layoutInflater)
            buttonBinding.root.id = ViewCompat.generateViewId()
            buttonBinding.viewModel = item
            binding.customRadiogroup.addView(buttonBinding.root)
            Log.d("zzz", "Inflated view with id ${buttonBinding.root.id}")
        }
    }*/
}