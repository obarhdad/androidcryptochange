package com.android.contact.android.crypto.change.core.internal.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun FragmentManager.inTransaction(@IdRes containerViewId: Int, fragment: Fragment, toBackStack: Boolean = true) {
    beginTransaction().apply {
        replace(containerViewId, fragment, fragment.tag)
        if (toBackStack) addToBackStack(fragment.tag)
    }.commit()
}
