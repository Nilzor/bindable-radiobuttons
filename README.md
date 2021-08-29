# Bindable Buttons, Radio Buttons and Checkboxes

Android databinding is an awesome tool that enabels a clean MVVM architecture. However lacking from
the core SDK is support for bound lists of buttons, radio buttons and checkboxes. These are pretty
normal use cases that I encounter in almost every app I develop. Therefore I have published this
library to aid in that.

# Implementation

Add a jitpack reference to your project using... // todo complete example 

# Usage

## Buttons

- Expose a list of `ButtonViewModel` objects in your ViewModel (1)   
- Define a layout file that has one and only one `<Button>` element. This will be the template for all buttons in the list. 
- Add a `BindableButtonList` to your view and wire up:
  - A reference to your view through the `app:buttonViewId` property 
  - A reference to your button list through the `app:buttons` property
  - (Optional) A reference to a click listener using the `onButtonClicked` property. However
    the most common approach would probably be wiring individual click listeners using the `onClick`
    event of the individual `ButtonViewModel`-classes.
    
Example: See `activity_main.xml` and `MainActivityViewModel` // todo insert reference
 
(1) If you don't want to use the provided `ButtonViewModel`, you can create your own button ViewModel extending `ButtonViewModelInterface` 

## Radio Buttons

- Expose a list of `RadioButtonViewModel` objects in your ViewModel 
- Define a layout file that has one and only one `<RadioButton>` element.
- Add a `BindableRadioButtonList` to your view and wire up:
  - A reference to your view through the `app:itemViewId` property 
  - A reference to your button list through the `app:buttons` property
  - (Optional) A one- or two-way binding to a viewmodel-property using the `app:selectedItem` property. 
    The field must be of type `TitledElement`, or `ObservableField<TitledElement>` if you want to listen 
    to or notify of changes to the checked states.
   
## Checkboxes

- Expose a list of `CheckBoxViewModel` objects in your ViewModel
- Define a layout file that has one and only one `<CheckBox>` element.
- Add a `BindableCheckBoxList` to your view and wire up:
  - A reference to your view through the `app:itemViewId` property 
  - A reference to your checkbox list through the `app:checkboxes` property
  - Use the `isChecked: ObservableBoolean` fields of the ViewModel if you need to react to changes.


# This library is opinionated

- It does not support a mutating list in the sense that individual additions or removal of elements
  take effect. If you need to apply changes to the list, rebind the entire list. 
  
- It does not allow the text of the buttons to change dyanamically. Most buttons should not
change title or function throughout their lifetime on their screen.
  
- It does not allow you to databind individual properties of the views other than click listeners and text.
  This would require a different approach or at least a custom binding interface that 
  takes away the simplicity of the library. If you _do_ need to databind e.g. the color of each check box text, 
  first ask yourself if you _really_ do need that, then either build the support from scratch, fork this
  library or make a pull request. 
  
   
# TODO
- todo: test java support
