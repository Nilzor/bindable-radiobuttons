package com.nilsenlabs.bindableradiobuttons.sample

import androidx.databinding.ObservableField
import com.nilsenlabs.bindableradiobuttons.buttons.BindableButtonList

class MyButtonViewModel(
    override var title: ObservableField<String>,
    override var onClick: BindableButtonList.ClickListener? = null
) : BindableButtonList.ButtonViewModel