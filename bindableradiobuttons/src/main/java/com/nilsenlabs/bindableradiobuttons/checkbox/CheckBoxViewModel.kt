package com.nilsenlabs.bindableradiobuttons.checkbox

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField


interface CheckBoxViewModel {
    val title: ObservableField<String>
    val isChecked: ObservableBoolean
}

class SimpleCheckBoxViewModel(
    title: String,
    isChecked: Boolean = false
) : CheckBoxViewModel {

    override val title: ObservableField<String> = ObservableField("")
    override val isChecked: ObservableBoolean = ObservableBoolean(false)

    init {
        this.title.set(title)
        this.isChecked.set(isChecked)
    }
}