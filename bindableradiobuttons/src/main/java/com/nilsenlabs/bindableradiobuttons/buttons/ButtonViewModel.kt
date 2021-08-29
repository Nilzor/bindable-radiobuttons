package com.nilsenlabs.bindableradiobuttons.buttons

import androidx.databinding.ObservableField

interface ButtonViewModel {
    // todo does this need to be observable
    var title: ObservableField<String>
    var onClick: BindableButtonList.ClickListener?
}