package com.nilsenlabs.bindableradiobuttons.checkbox

import androidx.databinding.ObservableBoolean
import com.nilsenlabs.bindableradiobuttons.TitledElement


interface CheckBoxViewModelInterface: TitledElement {
    override val title: String
    val isChecked: ObservableBoolean
    val isEnabled: Boolean
}

open class CheckBoxViewModel @JvmOverloads constructor(
    override val title: String,
    isChecked: Boolean = false,
    override val isEnabled: Boolean = true,
): CheckBoxViewModelInterface {
    override val isChecked = ObservableBoolean(isChecked)
    override fun toString() = "$title - checked: ${isChecked.get()}, enabled: $isEnabled"
}