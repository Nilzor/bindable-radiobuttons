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

    /**
     * Event fired when any of the buttons in the list is clicked.
     * The ViewModel of the clicked button is passed as parameter to the event.
     */
    var onButtonClicked: ButtonClickListener? = null

    private fun onClick(view: View) {
        val vm = view.tag as ButtonViewModel
        onButtonClicked?.onButtonClicked(vm)
        vm.onClick?.onClicked()
    }

    private fun reinflateViews() {
        removeAllViews()
        val inflater = LayoutInflater.from(context)
        for (buttonVm in buttons ?: emptyList()) {
            val buttonView = buttonViewId?.let { viewId ->
                inflater.inflate(viewId, this, false) as Button
            } ?: Button(context)
            buttonView.text = buttonVm.title
            buttonView.tag = buttonVm
            buttonView.setOnClickListener(::onClick)
            this.addView(buttonView)
        }
    }
}