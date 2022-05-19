package com.nhvn.todoandroidnative.data.datasources.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import org.parceler.Parcel
import java.util.*

@Parcel
@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey @ColumnInfo(name = "id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "state") val state: TodoWorkingStateEnum = TodoWorkingStateEnum.created,
    @ColumnInfo(name = "created_at") val createdAt: Date = Date()
)

enum class TodoWorkingStateEnum {
    created,
    inprocess,
    done,
}

object DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}