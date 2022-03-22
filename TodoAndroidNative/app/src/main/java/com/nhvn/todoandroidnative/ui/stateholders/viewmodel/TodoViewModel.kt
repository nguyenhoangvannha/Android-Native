package com.nhvn.todoandroidnative.ui.stateholders.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codelab.android.datastore.UserPreferences
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.repositories.TodosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val todoRepository: TodosRepository) : ViewModel() {

    val allWords: LiveData<List<Todo>> = todoRepository.allTodos.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(todo: Todo) = viewModelScope.launch {
        todoRepository.insert(todo)
    }

//    val todoPagingData: Flow<PagingData<Todo>> =
//        todoRepository.todoPager.flow.cachedIn(viewModelScope)

    fun getTodosByPage(): Flow<PagingData<Todo>> {
        return todoRepository.getTodosByPage().cachedIn(viewModelScope)
    }

    fun darkMode(): Flow<Boolean> {
        return todoRepository.darkMode()
    }

    fun setDarkMode(darkMode: Boolean) = viewModelScope.launch {
        todoRepository.setDarkMode(darkMode = darkMode)
    }

    fun userPreferences(): Flow<UserPreferences> {
        return todoRepository.userPreferencesFlow()
    }

    fun setDarkModeProtoStore(darkMode: Boolean) = viewModelScope.launch {
        todoRepository.setDarkModeProtoStore(darkMode = darkMode)
    }
}

class TodoViewModelFactory(private val repository: TodosRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}