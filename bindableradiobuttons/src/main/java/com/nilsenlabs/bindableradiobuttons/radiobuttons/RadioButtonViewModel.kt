package com.nilsenlabs.bindableradiobuttons.radiobuttons

import com.nilsenlabs.bindableradiobuttons.TitledElement

interface RadioButtonViewModelInterface : TitledElement

open class RadioButtonViewModel(override val title: String) : RadioButtonViewModelInterface {
    override fun toString() = title
}
