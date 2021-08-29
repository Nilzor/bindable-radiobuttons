package com.nilsenlabs.bindableradiobuttons.checkbox

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.ToggleButton
import androidx.annotation.IntegerRes
import androidx.databinding.Observable
import com.nilsenlabs.bindableradiobuttons.Consts

class BindableCheckBoxList(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    @IntegerRes
    var itemViewId: Int? = null
        set(value) {
            field = value
            reinflateViews()
        }

    var checkboxes: List<CheckBoxViewModel>? = null
        set(newVal) {
            field = newVal
            reinflateViews()
        }

    init {
        val viewId = attrs.getAttributeResourceValue(Consts.AppXmlNamespace, "itemViewId", 0)
        if (viewId != 0) itemViewId = viewId
    }

    private fun reinflateViews() {
        removeAllViews()
        val inflater = LayoutInflater.from(context)
        for (viewModel in checkboxes ?: emptyList()) {
            val view = itemViewId?.let { viewId ->
                inflater.inflate(viewId, this, false) as CompoundButton
            } ?: CheckBox(context)
            // Set bound properties
            view.text = viewModel.title
            (view as? ToggleButton)?.let {
                // For toggle buttons: Set the text on the button itself
                it.textOn = viewModel.title
                it.textOff = viewModel.title
            }

            view.isChecked = viewModel.isChecked.get()
            view.tag = viewModel

            view.setOnCheckedChangeListener { clickedView, isChecked ->
                (clickedView.tag as CheckBoxViewModel).isChecked.set(isChecked)
            }
            // Support two way databinding
            viewModel.isChecked.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    findViewWithTag<CompoundButton>(viewModel)?.isChecked = viewModel.isChecked.get()
                }
            })
            this.addView(view)
        }
    }
}