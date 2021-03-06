package com.nhvn.todoandroidnative.ui.stateholders.viewmodel

import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nhvn.todoandroidnative.data.ForegroundService
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.repositories.AbstractTodosRepository
import com.nhvn.todoandroidnative.data.repositories.TodosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: AbstractTodosRepository) :
    ViewModel() {


    val todos: LiveData<List<Todo>> = todoRepository.allTodos.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(todo: Todo) = viewModelScope.launch {
        todoRepository.insert(todo)
    }

    fun add(value: String): String {
        return value;
    }

//    val todoPagingData: Flow<PagingData<Todo>> =
//        todoRepository.todoPager.flow.cachedIn(viewModelScope)

//    fun getTodosByPage(): Flow<PagingData<Todo>> {
//        return todoRepository.getTodosByPage().cachedIn(viewModelScope)
//    }

//    fun darkMode(): Flow<Boolean> {
//        return todoRepository.darkMode()
//    }
//
//    fun setDarkMode(darkMode: Boolean) = viewModelScope.launch {
//        todoRepository.setDarkMode(darkMode = darkMode)
//    }
//
//    fun userPreferences(): Flow<UserPreferences> {
//        return todoRepository.userPreferencesFlow()
//    }
//
//    fun setDarkModeProtoStore(darkMode: Boolean) = viewModelScope.launch {
//        todoRepository.setDarkModeProtoStore(darkMode = darkMode)
//    }

//    var workChainInfoLiveData: LiveData<List<WorkInfo>> =
//        WorkManager.getInstance().getWorkInfosByTagLiveData(WORK_CHAIN_TAG)
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun doAWorkChain() {
//        todoRepository.doAWorkChain()
//    }
//
//    fun cancelWorkerByTag(tag: String) {
//        todoRepository.cancelWorkByTag(tag)
//    }
//
//    val makeBackgroundThreadWorkRequestData: MutableLiveData<Int> = MutableLiveData(0);
//
//    fun makeBackgroundThreadWorkRequest(input: Int) {
//        todoRepository.makeBackgroundThreadWorkRequest(input) { result ->
//            when (result) {
//                is Result.Success<Int> -> {
//                    Log.i("makeBackgroundThreadWorkRequest", result.data.toString())
//                    makeBackgroundThreadWorkRequestData.postValue(result.data!!)
//                }
//                else -> Log.i("makeBackgroundThreadWorkRequest", "Error")
//            }
//        }
//    }
//
//    val makeCoroutinesWorkRequestData: MutableLiveData<Int> = MutableLiveData(0);
//
//    fun makeCoroutinesWorkRequest(input: Int) {
//        // Create a new coroutine to move the execution off the UI thread
//        viewModelScope.launch {
//            todoRepository.makeCoroutinesWorkRequest(input) { result ->
//                when (result) {
//                    is Result.Success<Int> -> {
//                        Log.i("makeCoroutinesWorkRequest", result.data.toString())
//                        makeCoroutinesWorkRequestData.postValue(result.data!!)
//                    }
//                    else -> Log.i("makeCoroutinesWorkRequest", "Error")
//                }
//            }
//        }
//    }
//
//    fun doAForegroundWorker() {
//        todoRepository.doAForegroundWorker()
//    }
}

//class TodoViewModelFactory(private val repository: TodosRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return TodoViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

class TodoAndroidViewModel(application: Application) : AndroidViewModel(application) {
//    val serviceIntent = Intent(getApplication(), ForegroundService::class.java)
//
//    fun startService() {
//        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
//        ContextCompat.startForegroundService(getApplication(), serviceIntent)
//    }
//
//    fun stopService() {
//        val serviceIntent = Intent(getApplication(), ForegroundService::class.java)
//        //stopService(serviceIntent)
//    }
}