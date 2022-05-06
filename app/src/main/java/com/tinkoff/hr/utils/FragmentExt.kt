package com.tinkoff.hr.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String){
    Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
}