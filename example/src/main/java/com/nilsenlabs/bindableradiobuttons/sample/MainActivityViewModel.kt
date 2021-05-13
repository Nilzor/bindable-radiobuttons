package com.nilsenlabs.bindableradiobuttons.sample

import android.util.Log
import androidx.databinding.ObservableField
import com.nilsenlabs.bindableradiobuttons.BindableButtonList

class MainActivityViewModel {
    val buttons = listOf<MyButtonViewModel>(
        MyButtonViewModel(ObservableField("Start")),
        MyButtonViewModel(ObservableField("Restart")),
        MyButtonViewModel(ObservableField("Abort")),
    )

    fun onButtonClicked(viewModel: BindableButtonList.ButtonViewModel) {
        Log.d(TAG, "Button clicked: ${viewModel.title}")
    }

    val lol: String = "Hai"

    companion object {
        val TAG = "VM"
    }
}
