package com.nilsenlabs.bindableradiobuttons

import android.util.AttributeSet

object DesignTimeDataGenerator {
    /**
     * Generates view model objects for design time visualization in Android Studio.
     * Use by setting "tools:itemCount" and optionally "tools:itemPrefix"
     */
    fun <T> generate(attrs: AttributeSet, viewModelProducer: (str: String) -> T): List<T> {
        val designerItemCount = attrs.getAttributeIntValue(Consts.ToolsXmlNamespace, "itemCount", 0)
        val designerItemPrefix = attrs.getAttributeValue(Consts.ToolsXmlNamespace, "itemPrefix") ?: "Item"
        val viewModels = mutableListOf<T>()
        for (i in 1..designerItemCount) {
            val viewModel = viewModelProducer.invoke("$designerItemPrefix $i")
            viewModels.add(viewModel)
        }
        return viewModels
    }

    fun getDesignTimeNullValue(attrs: AttributeSet): Int {
        // For some reason AS (Chipmunk) designer does not return 0 as default value
        // but some Int, in my case -2132082686, for values that are not set.
        // In order to support correct design time behaviour we need this piece of code
        // The bogus string could be anything that doesn't exist.
        return attrs.getAttributeResourceValue(Consts.AppXmlNamespace, "bogus390gnklkgaasdgf34gaega", 0)
    }
}