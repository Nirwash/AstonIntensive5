package com.nirwashh.astonintensive5.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    var name: String,
    var lastName: String,
    var telNumber: String,
    val imageUrl: String,
    val id: Long
) : Parcelable
