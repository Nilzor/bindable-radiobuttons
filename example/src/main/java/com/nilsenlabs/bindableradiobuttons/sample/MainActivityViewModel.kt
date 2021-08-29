package com.nilsenlabs.bindableradiobuttons.sample

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.nilsenlabs.bindableradiobuttons.TitledElement
import com.nilsenlabs.bindableradiobuttons.buttons.ButtonViewModel
import com.nilsenlabs.bindableradiobuttons.checkbox.CheckBoxListViewModel
import com.nilsenlabs.bindableradiobuttons.checkbox.CheckBoxViewModel
import com.nilsenlabs.bindableradiobuttons.radiobuttons.RadioButtonViewModel

class MainActivityViewModel : BaseObservable(

) {
    val buttons = listOf<MyButtonViewModel>(
        MyButtonViewModel("Invert", this::invertCheckboxes),
        MyButtonViewModel("Clear"),
        MyButtonViewModel("Next", this::nextRadioButton),
    )

    val radioButtons = listOf(
        RadioButtonViewModel("One"),
        RadioButtonViewModel("Two"),
        RadioButtonViewModel("Three")
    )

    val checkBoxViewModel = CheckBoxListViewModel(
        CheckBoxViewModel("Alpha"),
        CheckBoxViewModel("Bravo"),
        CheckBoxViewModel("Charlie", true),
        CheckBoxViewModel("Delta")
    )

    val customRadioButtons = listOf(
        MyRadioButtonViewModel("One"),
        MyRadioButtonViewModel("Two", true),
        MyRadioButtonViewModel("Three")
    )

    val selectedRadioButton = ObservableField<TitledElement>(radioButtons[2])

    fun invertCheckboxes() {
        for (checkbox in checkBoxViewModel) {
            checkbox.isChecked.set(!checkbox.isChecked.get())
        }
    }

    fun nextRadioButton() {
        selectedRadioButton.get()?.let {
            val toSelect = (radioButtons.indexOf(it) + 1) % radioButtons.size
            selectedRadioButton.set(radioButtons.get(toSelect))
        }
    }

    fun onButtonClicked(viewModel: ButtonViewModel) {
        Log.d(TAG, "Button clicked: ${viewModel.title.get()}")
    }

    init {
        selectedRadioButton.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.d(TAG, "Radio button changed to ${selectedRadioButton.get()}")
            }
        })

        checkBoxViewModel.get(0).isChecked.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.d(TAG, "Alpha changed: " + (sender as ObservableBoolean).get())
            }
        })
    }

    companion object {
        val TAG = "VM"
    }
}
