package com.example.reddit.common.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.reddit.R
import dagger.android.AndroidInjection
import java.io.IOException

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        if (getContentResource() != -1) {
            setContentView(getContentResource())
        }
        init(savedInstanceState)
    }

    @LayoutRes
    abstract fun getContentResource(): Int

    abstract fun init(state: Bundle?)
}

fun AppCompatActivity.handleHttpException(e: Throwable) {
    if (e is IOException)
        Toast.makeText(
            baseContext,
            getString(R.string.msg_internet_connection_error),
            Toast.LENGTH_LONG
        ).show()
    else {
        Toast.makeText(
            baseContext,
            getString(R.string.msg_something_went_wrong),
            Toast.LENGTH_LONG
        ).show()
    }
}