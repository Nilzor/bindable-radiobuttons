package com.nilsenlabs.bindableradiobuttons.radiobuttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.IntegerRes
import com.nilsenlabs.bindableradiobuttons.Consts

class BindableRadioButtonList(context: Context, attrs: AttributeSet) : RadioGroup(context, attrs) {
    @IntegerRes
    var itemViewId: Int? = null
        set(value) {
            field = value
            reinflateViews()
        }

    init {
        val viewId = attrs.getAttributeResourceValue(Consts.AppXmlNamespace, "itemViewId", 0)
        if (viewId != 0) itemViewId = viewId
    }

    var buttons: List<RadioButtonViewModelInterface>? = null
        set(value) {
            field = value
            reinflateViews()
        }

    var selectedItem: RadioButtonViewModelInterface? = null
        set(value) {
            field = value
            findViewWithTag<RadioButton>(selectedItem)?.let { viewToSelect ->
                viewToSelect.isChecked = true
            }
        }
    
    private fun reinflateViews() {
        removeAllViews()
        val inflater = LayoutInflater.from(context)
        for (viewModel in buttons ?: emptyList()) {
            val view = itemViewId?.let { viewId ->
                inflater.inflate(viewId, this, false) as RadioButton
            } ?: RadioButton(context)
            // Set initial bound properties
            view.text = viewModel.title
            view.tag = viewModel
            if (viewModel == selectedItem) view.isChecked = true
            // Support two-way databinding
            view.setOnCheckedChangeListener { buttonView, isChecked -> 
                if (isChecked) selectedItem = buttonView.tag as RadioButtonViewModelInterface
            }
            this.addView(view)
        }
    }
}