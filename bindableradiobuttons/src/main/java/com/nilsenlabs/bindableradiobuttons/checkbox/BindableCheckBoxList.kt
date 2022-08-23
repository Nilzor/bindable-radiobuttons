package com.nilsenlabs.bindableradiobuttons.checkbox

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.ToggleButton
import androidx.annotation.LayoutRes
import androidx.databinding.Observable
import com.nilsenlabs.bindableradiobuttons.Consts
import com.nilsenlabs.bindableradiobuttons.DesignTimeDataGenerator

class BindableCheckBoxList(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    @LayoutRes
    var itemViewId: Int? = null
        set(value) {
            field = value
            reinflateViews()
        }

    var checkboxes: List<CheckBoxViewModelInterface>? = null
        set(newVal) {
            field = newVal
            reinflateViews()
        }

    /**
     * Event fired when any of the buttons in the list is clicked.
     * The ViewModel of the clicked button is passed as parameter to the event.
     */
    var onCheckedChanged: CheckedChangeListener? = null

    fun interface CheckedChangeListener {
        fun checkboxChecked(checkbox: CheckBoxViewModelInterface)
    }

    init {
        val viewId = attrs.getAttributeResourceValue(Consts.AppXmlNamespace, "itemViewId", 0)
        if (viewId != 0 && viewId != DesignTimeDataGenerator.getDesignTimeNullValue(attrs)) {
            itemViewId = viewId
        }
        checkboxes = DesignTimeDataGenerator.generate(attrs) { CheckBoxViewModel(it) }
    }

    private fun reinflateViews() {
        removeAllViews()
        val inflater = LayoutInflater.from(context)
        for (viewModel in checkboxes ?: emptyList()) {
            val view: CompoundButton = itemViewId?.let { viewId ->
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
            view.isEnabled = viewModel.isEnabled

            view.setOnCheckedChangeListener { clickedView, isChecked ->
                (clickedView.tag as? CheckBoxViewModelInterface)?.let { viewModel ->
                    viewModel.isChecked.set(isChecked)
                    onCheckedChanged?.checkboxChecked(viewModel)
                }
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