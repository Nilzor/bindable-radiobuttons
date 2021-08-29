package com.nilsenlabs.bindableradiobuttons.buttons

import androidx.databinding.ObservableField

interface ButtonViewModel {
    var title: ObservableField<String>
    var onClick: BindableButtonList.ClickListener?
}