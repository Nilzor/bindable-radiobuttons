package com.nilsenlabs.bindableradiobuttons.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
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

    init {
        val viewId = attrs.getAttributeResourceValue(Consts.AppXmlNamespace, "itemViewId", 0)
        if (viewId != 0) itemViewId = viewId
    }

    var checkboxes: CheckBoxListViewModel = CheckBoxListViewModel()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun addOnAttachStateChangeListener(listener: OnAttachStateChangeListener?) {
        // memleak here?
    }

    private fun reinflateViews() {
        removeAllViews()
        val inflater = LayoutInflater.from(context)
        for (viewModel in checkboxes) {
            val view = itemViewId?.let { viewId ->
                inflater.inflate(viewId, this, false) as CheckBox
            } ?: CheckBox(context)
            view.text = viewModel.text
            view.tag = viewModel
            view.setOnCheckedChangeListener { clickedView, isChecked ->
                (clickedView.tag as CheckBoxViewModel).isChecked.set(isChecked)
            }
            // Todo memloead
            viewModel.isChecked.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    findViewWithTag<CheckBox>(viewModel)?.isChecked = viewModel.isChecked.get()
                }
            })
            this.addView(view)
        }
    }
}