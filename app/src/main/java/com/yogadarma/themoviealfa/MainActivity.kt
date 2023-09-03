package com.yogadarma.themoviealfa

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.yogadarma.common.base.BaseActivity
import com.yogadarma.common.navigation.NavigationDirection
import com.yogadarma.themoviealfa.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onView() {
        lifecycleScope.launch {
            delay(2000)
            navigateTo(
                NavigationDirection.MainList,
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
        }
    }
}