package com.nilsenlabs.bindableradiobuttons.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.annotation.StringRes

class BindableCustomButtonList(context: Context, attrs: AttributeSet) : RadioGroup(context, attrs) {
    interface OnDatabindSetup {
        fun setupDatabinding(button: Button)
    }

    var onDatabindSetup: OnDatabindSetup? = null

    @StringRes
    var buttonViewId: Int? = null

    private fun reinflateViews() {
        val inflater = LayoutInflater.from(context)
        this.removeAllViews()

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}