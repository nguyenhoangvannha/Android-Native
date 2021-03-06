package com.nhvn.todoandroidnative.data.datasources.daos

import androidx.room.*
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getAll(): List<Todo>

    @Query("SELECT * FROM todo_table LIMIT :limit OFFSET :offset")
    fun getByPage(limit: Int = 7, offset: Int): List<Todo>

    @Query("SELECT * FROM todo_table ORDER BY title ASC")
    fun getAlphabetizedTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo_table WHERE id IN (:ids)")
    fun loadAllByIds(ids: List<String>): List<Todo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Todo)

    @Delete
    fun delete(todo: Todo)
}