package com.nilsenlabs.bindableradiobuttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.BinderThread
import androidx.annotation.StringRes
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding

class BindableCustomButtonList(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    interface OnDatabindSetup {
        fun setupDatabinding(button: Button)
    }

    var buttons: List<Any>? = null
        set(value) {
            field = value
            reinflateViews()
        }

    var onDatabindSetup: OnDatabindSetup? = null

    @StringRes
    var buttonViewId: Int? = null

    private fun reinflateViews() {
        val inflater = LayoutInflater.from(context)
        this.removeAllViews()
        buttonViewId?.let { viewId ->
            for (buttonVm in buttons ?: emptyList()) {
                val buttonView = inflater.inflate(viewId, this, false) as Button
                onDatabindSetup?.let { }
                this.addView(buttonView)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}