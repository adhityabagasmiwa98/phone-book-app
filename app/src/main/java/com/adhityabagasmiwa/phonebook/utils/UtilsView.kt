package com.adhityabagasmiwa.phonebook.utils

import android.view.View
import android.widget.Toast

fun View.showMessage(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}