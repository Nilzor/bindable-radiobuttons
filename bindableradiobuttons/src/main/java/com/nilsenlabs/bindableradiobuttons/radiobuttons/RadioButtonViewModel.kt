package com.nilsenlabs.bindableradiobuttons.radiobuttons

interface TitledElement {
    val title: String
}

data class SimpleTitledElement(override val title: String) : TitledElement
