package com.android.contact.android.crypto.change.core.internal.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadAvatar(url: String?) {
    url?.let {
        Picasso.get().load(url).into(this)
    }
}
