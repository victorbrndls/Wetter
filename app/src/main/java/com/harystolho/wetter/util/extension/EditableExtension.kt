package com.harystolho.wetter.util.extension

import android.text.Editable

fun editableOf(value: String) = Editable.Factory().newEditable(value)