package com.nilsenlabs.bindableradiobuttons.radiobuttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.LayoutRes
import com.nilsenlabs.bindableradiobuttons.Consts
import com.nilsenlabs.bindableradiobuttons.DesignTimeDataGenerator

class BindableRadioButtonList(context: Context, attrs: AttributeSet) : RadioGroup(context, attrs) {
    @LayoutRes
    var itemViewId: Int? = null
        set(value) {
            field = value
            reinflateViews()
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

    init {
        val viewId = attrs.getAttributeResourceValue(Consts.AppXmlNamespace, "itemViewId", 0)
        if (viewId != 0 && viewId != DesignTimeDataGenerator.getDesignTimeNullValue(attrs)) {
            itemViewId = viewId
        }
        buttons = DesignTimeDataGenerator.generate(attrs) { RadioButtonViewModel(it) }
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
            view.isEnabled = viewModel.isEnabled
            if (viewModel == selectedItem) view.isChecked = true
            // Support two-way databinding
            view.setOnCheckedChangeListener { buttonView, isChecked -> 
                if (isChecked) selectedItem = buttonView.tag as RadioButtonViewModelInterface
            }
            this.addView(view)
        }
    }
}