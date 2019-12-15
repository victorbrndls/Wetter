package com.harystolho.wetter.util.extension

import android.view.View
import com.harystolho.wetter.util.listener.SafeClickListener

fun View.setOnSafeClickListener(block: () -> Unit) = this.setOnClickListener(SafeClickListener(block))