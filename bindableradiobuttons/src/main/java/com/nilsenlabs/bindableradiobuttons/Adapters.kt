package com.nilsenlabs.bindableradiobuttons

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.nilsenlabs.bindableradiobuttons.radiobuttons.BindableRadioButtonList

object Adapters {
    //https://developer.android.com/reference/android/databinding/InverseBindingAdapter
    @JvmStatic
    @InverseBindingAdapter(attribute = "app:selectedItem", event = "app:selectedItemAttrChanged")
    fun selectedItem(brb: BindableRadioButtonList) : TitledElement? {
        return brb.selectedItem
    }

    @JvmStatic
    @BindingAdapter("app:selectedItemAttrChanged")
    fun setTitledElementChanged(brb: BindableRadioButtonList, ibl: InverseBindingListener?) {
        if (ibl != null) {
            brb.setOnCheckedChangeListener { group, checkedId ->
                ibl.onChange()
            }
        }
        ibl?.onChange()
    }
}