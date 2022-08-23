package com.nilsenlabs.bindableradiobuttons.sample

import android.util.Log
import androidx.databinding.*
import com.nilsenlabs.bindableradiobuttons.buttons.ButtonViewModel
import com.nilsenlabs.bindableradiobuttons.buttons.ButtonViewModelInterface
import com.nilsenlabs.bindableradiobuttons.checkbox.BindableCheckBoxList
import com.nilsenlabs.bindableradiobuttons.checkbox.CheckBoxViewModel
import com.nilsenlabs.bindableradiobuttons.radiobuttons.RadioButtonViewModel
import com.nilsenlabs.bindableradiobuttons.radiobuttons.RadioButtonViewModelInterface


class MainActivityViewModel : BaseObservable() {
    companion object {
        const val TAG = "VM"
    }

    val buttons = listOf(
        ButtonViewModel("Invert check", true, this::invertCheckboxes),
        ButtonViewModel("Next radio", true, this::selectNextRadioButton),
        ButtonViewModel("Toggle checkbox type", true, this::toggleCheckBoxType),
        ButtonViewModel("D", false),
    )

    val radioButtons = listOf(
        RadioButtonViewModel("One"),
        RadioButtonViewModel("Two", isEnabled = false),
        RadioButtonViewModel("Three")
    )

    val checkboxes = listOf(
        CheckBoxViewModel("Alpha"),
        CheckBoxViewModel("Bravo", isEnabled = false),
        CheckBoxViewModel("Charlie", isChecked = true),
        CheckBoxViewModel("Delta")
    )

    val checkBoxButtonView = ObservableInt(R.layout.my_checkbox)

    val selectedRadioButton = ObservableField<RadioButtonViewModelInterface>(radioButtons[2])

    val onCheckedChanged = BindableCheckBoxList.CheckedChangeListener { model ->
        Log.d(TAG, "Check changed through list of checkboxes: $model")
    }

    fun onButtonClicked(viewModel: ButtonViewModelInterface) {
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
                Log.d(TAG, "Checkbox 'Alpha' changed state to checked? " + (sender as ObservableBoolean).get())
            }
        })
    }

    private fun invertCheckboxes() {
        for (checkbox in checkboxes) {
            checkbox.isChecked.set(!checkbox.isChecked.get())
        }
    }

    private fun selectNextRadioButton() {
        selectedRadioButton.get()?.let {
            val toSelect = (radioButtons.indexOf(it) + 1) % radioButtons.size
            selectedRadioButton.set(radioButtons.get(toSelect))
        }
    }

    private fun toggleCheckBoxType() {
        if (checkBoxButtonView.get() == R.layout.my_togglebutton) {
            checkBoxButtonView.set(R.layout.my_checkbox)
        } else {
            checkBoxButtonView.set(R.layout.my_togglebutton)
        }
    }
}
