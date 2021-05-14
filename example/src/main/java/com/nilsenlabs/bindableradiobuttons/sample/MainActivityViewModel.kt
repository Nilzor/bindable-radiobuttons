package com.nilsenlabs.bindableradiobuttons.sample

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.nilsenlabs.bindableradiobuttons.buttons.BindableButtonList
import com.nilsenlabs.bindableradiobuttons.radiobuttons.SimpleTitledElement
import com.nilsenlabs.bindableradiobuttons.radiobuttons.TitledElement

class MainActivityViewModel {
    val buttons = listOf<MyButtonViewModel>(
        MyButtonViewModel(ObservableField("Start")),
        MyButtonViewModel(ObservableField("Restart")),
        MyButtonViewModel(ObservableField("Abort")),
    )

    val radioButtons = listOf(
        SimpleTitledElement("One"),
        SimpleTitledElement("Two"),
        SimpleTitledElement("Three")
    )
    val selectedRadioButton = ObservableField<TitledElement>(radioButtons[1])

    fun onButtonClicked(viewModel: BindableButtonList.ButtonViewModel) {
        Log.d(TAG, "Button clicked: ${viewModel.title.get()}")
    }

    init {
        selectedRadioButton.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.d(TAG, "Radio button changed to ${selectedRadioButton.get()}")
            }

        })
    }

    companion object {
        val TAG = "VM"
    }
}
