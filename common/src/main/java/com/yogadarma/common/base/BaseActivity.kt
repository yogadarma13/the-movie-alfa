package com.yogadarma.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yogadarma.common.navigation.NavigationDirection

abstract class BaseActivity<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    AppCompatActivity() {

    private var _binding: VB? = null

    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(_binding?.root)

        onView()
    }

    abstract fun onView()

    private fun getBaseApp() = application as? BaseApp

    fun navigateTo(
        direction: NavigationDirection,
        intentFlags: Int? = null
    ) {
        getBaseApp()?.navigateTo(
            this,
            direction,
            intentFlags
        )

    }
}
