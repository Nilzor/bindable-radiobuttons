package com.nilsenlabs.bindableradiobuttons.radiobuttons

interface TitledElement {
    val title: String
}

// todo move to app
interface RadioButtonsViewModel {
    val buttons: List<TitledElement>
    val checkedElement: TitledElement?
}

data class SimpleTitledElement(override val title: String) : TitledElement

class SimpleRadioButtonsViewModel(
    override val buttons: List<SimpleTitledElement>,
    override val checkedElement: SimpleTitledElement?
) : RadioButtonsViewModel

class GenericRadioButtonsViewModel<T : TitledElement>(
    override val buttons: List<T>,
    override val checkedElement: T?
) : RadioButtonsViewModel