package com.nilsenlabs.bindableradiobuttons.checkbox

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList


open class CheckBoxListViewModel : ObservableList<CheckBoxViewModel> by ObservableArrayList()

open class CheckBoxViewModel(val text: String, isChecked: Boolean) {
    val isChecked = ObservableBoolean(isChecked)
}
