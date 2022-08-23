package com.nilsenlabs.bindableradiobuttons.buttons


interface ButtonViewModelInterface {
    val title: String
    val isEnabled: Boolean
    val onClick: BindableButtonList.ClickListener?
}

open class ButtonViewModel @JvmOverloads constructor(
    override val title: String,
    override val isEnabled: Boolean = true,
    override val onClick: BindableButtonList.ClickListener? = null
) : ButtonViewModelInterface {
    override fun toString() = "$title - enabled: $isEnabled"
}