package com.nilsenlabs.bindableradiobuttons.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nilsenlabs.bindableradiobuttons.R
import com.nilsenlabs.bindableradiobuttons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = MainActivityViewModel()
        setContentView(binding.root)
    }
}