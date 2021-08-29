package com.nilsenlabs.bindableradiobuttons.checkbox

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import com.nilsenlabs.bindableradiobuttons.TitledElement


open class CheckBoxListViewModel() : ObservableList<CheckBoxViewModel> by ObservableArrayList() {
    constructor(vararg checkBoxes: CheckBoxViewModel) : this() {
        addAll(checkBoxes)
    }
}

interface CheckBoxViewModelInterface: TitledElement {
    override val title: String
    val isChecked: ObservableBoolean
}

open class CheckBoxViewModel(override val title: String, isChecked: Boolean = false): CheckBoxViewModelInterface {
    override val isChecked = ObservableBoolean(isChecked)
}
