package com.nhvn.todoandroidnative.models

import org.parceler.Parcel
import java.util.*

@Parcel
data class TodoModel(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String = ""
)
