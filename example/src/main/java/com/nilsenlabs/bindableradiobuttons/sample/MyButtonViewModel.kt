package com.nilsenlabs.bindableradiobuttons.sample

import androidx.databinding.ObservableField
import com.nilsenlabs.bindableradiobuttons.BindableButtonList

class MyButtonViewModel(
    override var title: ObservableField<String>,
    override var onClick: BindableButtonList.ClickListener? = null
) : BindableButtonList.ButtonViewModel