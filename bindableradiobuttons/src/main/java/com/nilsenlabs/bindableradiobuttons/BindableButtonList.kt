package com.nilsenlabs.bindableradiobuttons

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList

class BindableButtonList(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        val q = 1
    }
    interface ButtonClickListener {
        fun onButtonClicked(buttonViewModel: ButtonViewModel)
    }

    interface ClickListener {
        fun onClicked()
    }

    interface ButtonViewModel {
        var title: ObservableField<String>
        var onClick: ClickListener?
    }

    var buttons: List<ButtonViewModel>? = null
        set(value) {
            field = value
            reinflateViews()
        }

    @IntegerRes
    var buttonViewId: Int? = null
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
        clear()
        val inflater = LayoutInflater.from(context)
        buttonViewId?.let { viewId ->
            for (buttonVm in buttons ?: emptyList()) {
                val buttonView = inflater.inflate(viewId, this, false) as Button
                buttonView.text = buttonVm.title.get()
                buttonView.tag = buttonVm
                buttonView.setOnClickListener(::onClick)
                // todo observe text change and list change of buttons if
                this.addView(buttonView)
            }
        }
    }

    private fun clear() {
        /*
        for (view in children) {
            (view as? Button)?.let { button ->
                button.setOnClickListener(null)
            }
        }*/
        removeAllViews()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}