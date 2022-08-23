package com.nilsenlabs.bindableradiobuttons.checkbox

import androidx.databinding.ObservableBoolean
import com.nilsenlabs.bindableradiobuttons.TitledElement


interface CheckBoxViewModelInterface: TitledElement {
    override val title: String
    val isChecked: ObservableBoolean
}

open class CheckBoxViewModel @JvmOverloads constructor(
    override val title: String,
    isChecked: Boolean = false
): CheckBoxViewModelInterface {
    override val isChecked = ObservableBoolean(isChecked)
    override fun toString() = "$title - checked: ${isChecked.get()}"
}