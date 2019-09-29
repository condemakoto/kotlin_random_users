package com.conde.kun.randomusers.dynamicfeature

import android.content.Intent

class DynamicFeatureLoader {

    private val PACKAGE_NAME = "com.conde.kun.randomusers"

    fun intentTo(className: String): Intent {
        return Intent(Intent.ACTION_VIEW).setClassName(
            PACKAGE_NAME,
            className
        )
    }

    private fun loadIntentOrNull(className: String): Intent? {
        try {
            Class.forName(className).run { return intentTo(className) }
        } catch (ex: Exception) {
            return null
        }
    }

    fun openUserSupportScreen(className: String): Intent? {
        return loadIntentOrNull(className)
    }

}