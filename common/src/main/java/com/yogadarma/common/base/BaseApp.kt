package com.yogadarma.common.base

import android.app.Application
import android.content.Context
import com.yogadarma.common.navigation.NavigationDirection

abstract class BaseApp : Application() {

    abstract fun navigateTo(
        context: Context,
        direction: NavigationDirection,
        flags: Int?
    )
}