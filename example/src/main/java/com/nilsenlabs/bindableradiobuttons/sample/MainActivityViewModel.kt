package com.nilsenlabs.bindableradiobuttons.sample

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.nilsenlabs.bindableradiobuttons.TitledElement
import com.nilsenlabs.bindableradiobuttons.buttons.ButtonViewModel
import com.nilsenlabs.bindableradiobuttons.radiobuttons.RadioButtonViewModel
import com.nilsenlabs.bindableradiobuttons.checkbox.CheckBoxListViewModel
import com.nilsenlabs.bindableradiobuttons.checkbox.CheckBoxViewModel

class MainActivityViewModel : BaseObservable() {
    companion object {
        val TAG = "VM"
    }

    val buttons = listOf(
        ButtonViewModel("Invert check", this::invertCheckboxes),
        ButtonViewModel("Next radio", this::selectNextRadioButton)
    )

    val radioButtons = listOf(
        RadioButtonViewModel("One"),
        RadioButtonViewModel("Two"),
        RadioButtonViewModel("Three")
    )

    val checkboxes = CheckBoxListViewModel(
        CheckBoxViewModel("Alpha"),
        CheckBoxViewModel("Bravo"),
        CheckBoxViewModel("Charlie", true),
        CheckBoxViewModel("Delta")
    )

    val selectedRadioButton = ObservableField<TitledElement>(radioButtons[2])

    fun onButtonClicked(viewModel: ButtonViewModel) {
        Log.d(TAG, "Button clicked: ${viewModel.title}")
    }

    init {
        listenForChanges()
    }

    private fun listenForChanges() {
        // Example illustrating how we can listen for changes in radio button changed
        selectedRadioButton.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.d(TAG, "Radio button changed to ${selectedRadioButton.get()}")
            }
        })

        // Example illustrating how we can listen for changes in checked state on the checkboxes
        checkboxes.get(0).isChecked.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.d(TAG, "Checkbox 'One' changed state to checked? " + (sender as ObservableBoolean).get())
            }
        })
    }

    fun invertCheckboxes() {
        for (checkbox in checkboxes) {
            checkbox.isChecked.set(!checkbox.isChecked.get())
        }
    }

    fun selectNextRadioButton() {
        selectedRadioButton.get()?.let {
            val toSelect = (radioButtons.indexOf(it) + 1) % radioButtons.size
            selectedRadioButton.set(radioButtons.get(toSelect))
        }
    }
}
