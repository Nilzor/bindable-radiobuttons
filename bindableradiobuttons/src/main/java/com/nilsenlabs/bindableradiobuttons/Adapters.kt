package com.nilsenlabs.bindableradiobuttons

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.nilsenlabs.bindableradiobuttons.radiobuttons.BindableRadioButtonList
import com.nilsenlabs.bindableradiobuttons.radiobuttons.TitledElement

/*
@InverseBindingMethods(
    InverseBindingMethod(type = BindableRadioButtonList::class, attribute = "appselectedItem", method = "getSeleecteedItem")
)*/
object Adapters {
    //https://developer.android.com/reference/android/databinding/InverseBindingAdapter
    @JvmStatic
    @InverseBindingAdapter(attribute = "app:selectedItem", event = "app:selectedItemAttrChanged")
    fun selectedItem(brb: BindableRadioButtonList) : TitledElement? {
        Log.d("zzz", "bindingAdapter GET")
        return brb.selectedItem
    }

    // todo: idea: Just binding adapter between viewId and bound item in tAG

    @JvmStatic
    //@BindingAdapter( attribute = "app:selectedItem", event = "android:selectedItemAttrChanged")
    @BindingAdapter("app:selectedItemAttrChanged")
    fun setTitledElementChanged(brb: BindableRadioButtonList, ibl: InverseBindingListener?) {
        Log.d("zzz", "bindingAdapter setTitledElementChanged")

        if (ibl != null) {
            brb.setOnCheckedChangeListener { group, checkedId ->
                ibl.onChange()
            }
        }
        ibl?.onChange()
    }
}