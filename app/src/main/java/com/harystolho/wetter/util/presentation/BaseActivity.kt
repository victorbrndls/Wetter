package com.harystolho.wetter.util.presentation

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected fun showMessage(message: Message) {
        Toast.makeText(this, message.getMessage(this), Toast.LENGTH_LONG).show()
    }

}