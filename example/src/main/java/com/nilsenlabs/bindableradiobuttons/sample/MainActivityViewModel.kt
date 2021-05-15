package com.nilsenlabs.bindableradiobuttons.sample

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.nilsenlabs.bindableradiobuttons.BR
import com.nilsenlabs.bindableradiobuttons.R

import com.nilsenlabs.bindableradiobuttons.buttons.BindableButtonList
import com.nilsenlabs.bindableradiobuttons.radiobuttons.SimpleTitledElement
import com.nilsenlabs.bindableradiobuttons.radiobuttons.TitledElement

class MainActivityViewModel : BaseObservable(

) {
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
    val selectedRadioButton = ObservableField<TitledElement>()//adioButtons[1])

    fun onButtonClicked(viewModel: BindableButtonList.ButtonViewModel) {
        Log.d(TAG, "Button clicked: ${viewModel.title.get()}")
        testChecked = R.id.other2
    }

    init {
        selectedRadioButton.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.d(TAG, "Radio button changed to ${selectedRadioButton.get()}")
            }
        })
    }

    @Bindable
    var testChecked: Int = -1
        get() { return field
            Log.d(TAG, "Someone asked for testChecked $field")}
        set(value) {
            field = value
            notifyPropertyChanged(BR.testChecked)
            Log.d(TAG, "TestChecked changd to $value")
        }

    companion object {
        val TAG = "VM"
    }
}
