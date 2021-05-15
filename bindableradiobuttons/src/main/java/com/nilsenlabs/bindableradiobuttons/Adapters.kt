package com.nilsenlabs.bindableradiobuttons

import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.nilsenlabs.bindableradiobuttons.radiobuttons.BindableRadioButtonList
import com.nilsenlabs.bindableradiobuttons.radiobuttons.TitledElement

object Adapters {
    //https://developer.android.com/reference/android/databinding/InverseBindingAdapter
    @InverseBindingAdapter(attribute = "app:selectedItem", event = "android:selectedItemAttrChanged")
    fun radioButtonGroupTitledElementChanged(brb: BindableRadioButtonList) : TitledElement? {
        return brb.selectedItem
    }

    @JvmStatic
    fun setTitledElementChanged(brb: BindableRadioButtonList, ibl: InverseBindingListener?) {
        //ibl?.onChange()
    }
}