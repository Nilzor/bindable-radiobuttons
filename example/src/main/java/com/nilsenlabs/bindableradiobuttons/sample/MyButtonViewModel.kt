package com.nilsenlabs.bindableradiobuttons.sample

import androidx.databinding.ObservableField
import com.nilsenlabs.bindableradiobuttons.buttons.BindableButtonList
import com.nilsenlabs.bindableradiobuttons.buttons.ButtonViewModel

class MyButtonViewModel(
    override var title: ObservableField<String>,
    override var onClick: BindableButtonList.ClickListener? = null
) : ButtonViewModel {
    constructor(title: String, onClick: BindableButtonList.ClickListener? = null): this(ObservableField(title), onClick)
}