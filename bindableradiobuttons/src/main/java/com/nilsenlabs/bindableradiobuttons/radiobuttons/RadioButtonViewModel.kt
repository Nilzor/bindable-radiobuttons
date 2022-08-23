package com.nilsenlabs.bindableradiobuttons.radiobuttons

import com.nilsenlabs.bindableradiobuttons.TitledElement

interface RadioButtonViewModelInterface : TitledElement {
    val isEnabled: Boolean
}

open class RadioButtonViewModel(
    override val title: String,
    override val isEnabled: Boolean = true,
) : RadioButtonViewModelInterface {
    override fun toString() = "$title - enabled: $isEnabled"
}
