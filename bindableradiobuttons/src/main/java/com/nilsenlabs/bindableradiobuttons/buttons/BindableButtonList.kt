package com.nilsenlabs.bindableradiobuttons.buttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.IntegerRes
import com.nilsenlabs.bindableradiobuttons.Consts

class BindableButtonList(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    @IntegerRes
    var buttonViewId: Int? = null
        set(value) {
            field = value
            reinflateViews()
        }

    init {
        val viewId = attrs.getAttributeResourceValue(Consts.AppXmlNamespace, "buttonViewId", 0)
        if (viewId != 0) buttonViewId = viewId
    }

    fun interface ButtonClickListener {
        fun onButtonClicked(buttonViewModel: ButtonViewModel)
    }

    fun interface ClickListener {
        fun onClicked()
    }

    var buttons: List<ButtonViewModel>? = null
        set(value) {
            field = value
            reinflateViews()
        }

    var onButtonClicked: ButtonClickListener? = null

    private fun onClick(view: View) {
        val vm = view.tag as ButtonViewModel
        onButtonClicked?.onButtonClicked(vm)
        vm.onClick?.onClicked()
    }

    private fun reinflateViews() {
        removeAllViews()
        val inflater = LayoutInflater.from(context)
        buttonViewId?.let { viewId ->
            for (buttonVm in buttons ?: emptyList()) {
                val buttonView = inflater.inflate(viewId, this, false) as Button
                buttonView.text = buttonVm.title
                buttonView.tag = buttonVm
                buttonView.setOnClickListener(::onClick)
                this.addView(buttonView)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}