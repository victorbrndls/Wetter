package com.harystolho.wetter.util.listener

import android.view.View

class SafeClickListener(private val block: () -> Unit, private val waitTime: Long = 1000) :
    View.OnClickListener {

    private var lastClick = -1L

    override fun onClick(v: View?) {
        val now = System.currentTimeMillis()

        if (lastClick + waitTime < now) {
            lastClick = now
            block()
        }
    }

}