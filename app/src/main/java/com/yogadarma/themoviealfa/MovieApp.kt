package com.yogadarma.themoviealfa

import android.content.Context
import android.content.Intent
import android.util.Log
import com.yogadarma.common.base.BaseApp
import com.yogadarma.common.navigation.NavigationDirection
import com.yogadarma.themoviealfa.navigation.activityMapper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp : BaseApp() {
    override fun navigateTo(context: Context, direction: NavigationDirection, flags: Int?) {
        val intent = Intent(context, activityMapper[direction::class.java]).apply {
            direction.extras.forEach { putExtra(it) }
            flags?.let { addFlags(it) }
        }
        context.startActivity(intent)
    }

    private fun Intent.putExtra(it: Map.Entry<String, Any?>) {
        when (val value = it.value) {
            is String? -> putExtra(it.key, value)
            else -> Log.e(
                "ERROR",
                "Intent extra ${it.key} has wrong type ${value?.javaClass?.name}"
            )
        }
    }

}