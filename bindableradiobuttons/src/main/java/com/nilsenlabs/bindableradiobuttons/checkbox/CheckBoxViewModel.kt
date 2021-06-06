package com.nilsenlabs.bindableradiobuttons.checkbox

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList


open class CheckBoxListViewModel() : ObservableList<CheckBoxViewModel> by ObservableArrayList() {
    constructor(vararg checkBoxes: CheckBoxViewModel) : this() {
        for (elem in checkBoxes) add(elem)
    }
}

open class CheckBoxViewModel(val text: String, isChecked: Boolean = false) {
    val isChecked = ObservableBoolean(isChecked)
}
