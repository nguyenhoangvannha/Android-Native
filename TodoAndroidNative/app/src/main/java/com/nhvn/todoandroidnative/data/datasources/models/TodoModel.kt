package com.nhvn.todoandroidnative.data.datasources.models

import org.parceler.Parcel
import java.util.*

@Parcel
data class TodoModel(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String = "",
    val state: TodoWorkingStateEnum = TodoWorkingStateEnum.created,
    val createdAt: Date = Date()
)

enum class TodoWorkingStateEnum {
    created,
    inprocess,
    done,
}
