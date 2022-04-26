package com.nhvn.todoandroidnative.ui.stateholders.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.codelab.android.datastore.UserPreferences
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.repositories.Result
import com.nhvn.todoandroidnative.data.repositories.TodosRepository
import com.nhvn.todoandroidnative.data.repositories.WORK_CHAIN_TAG
import com.nhvn.todoandroidnative.data.work.Worker1
import com.nhvn.todoandroidnative.data.work.Worker2
import com.nhvn.todoandroidnative.data.work.Worker3
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    var workChainInfoLiveData: LiveData<List<WorkInfo>> =
        WorkManager.getInstance().getWorkInfosByTagLiveData(WORK_CHAIN_TAG)

    @RequiresApi(Build.VERSION_CODES.O)
    fun doAWorkChain() {
        todoRepository.doAWorkChain()
    }

    fun cancelWorkerByTag(tag: String) {
        todoRepository.cancelWorkByTag(tag)
    }

    val makeBackgroundThreadWorkRequestData: MutableLiveData<Int> = MutableLiveData(0);

    fun makeBackgroundThreadWorkRequest(input: Int) {
        todoRepository.makeBackgroundThreadWorkRequest(input) { result ->
            when (result) {
                is Result.Success<Int> -> {
                    Log.i("makeBackgroundThreadWorkRequest", result.data.toString())
                    makeBackgroundThreadWorkRequestData.postValue(result.data!!)
                }
                else -> Log.i("makeBackgroundThreadWorkRequest", "Error")
            }
        }
    }

    val makeCoroutinesWorkRequestData: MutableLiveData<Int> = MutableLiveData(0);

    fun makeCoroutinesWorkRequest(input: Int) {
        // Create a new coroutine to move the execution off the UI thread
        viewModelScope.launch {
            todoRepository.makeCoroutinesWorkRequest(input) { result ->
                when (result) {
                    is Result.Success<Int> -> {
                        Log.i("makeCoroutinesWorkRequest", result.data.toString())
                        makeCoroutinesWorkRequestData.postValue(result.data!!)
                    }
                    else -> Log.i("makeCoroutinesWorkRequest", "Error")
                }
            }
        }
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