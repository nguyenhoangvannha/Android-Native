package com.nhvn.todoandroidnative.ui.di

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat
import androidx.datastore.dataStore
import androidx.paging.PagingSource
import com.nhvn.todoandroidnative.data.datasources.*
import com.nhvn.todoandroidnative.data.datasources.daos.TodoDao
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.datasources.paging.TodoPagingSource
import com.nhvn.todoandroidnative.data.repositories.AbstractTodosRepository
import com.nhvn.todoandroidnative.data.repositories.TodosRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    fun provideTodosApi(): TodosApi {
        return TodosApiImpl()
    }

    @Provides
    fun provideTodosRemoteDataSource(todosApi: TodosApi): TodosRemoteDataSource {
        return TodosRemoteDataSource(todosApi)
    }

    @Provides
    fun provideTodosLocalDataSource(todoDao: TodoDao): TodosLocalDataSource {
        return TodosLocalDataSource(todoDao)
    }

    @Provides
    fun provideTodoPagingSource(todosLocalDataSource: TodosLocalDataSource): PagingSource<Int, Todo> {
        return TodoPagingSource(todosLocalDataSource)
    }


    @Provides
    fun provideExecutor(): Executor {
        /*
       * Gets the number of available cores
       * (not always the same as the maximum number of cores)
       */
        val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()

        // Instantiates the queue of Runnables as a LinkedBlockingQueue
        val workQueue: BlockingQueue<Runnable> =
            LinkedBlockingQueue<Runnable>()

        // Sets the amount of time an idle thread waits before terminating
        val KEEP_ALIVE_TIME = 1L

        // Sets the Time Unit to seconds
        val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS

        // Creates a thread pool manager
        val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
            NUMBER_OF_CORES,       // Initial pool size
            NUMBER_OF_CORES,       // Max pool size
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            workQueue
        )

        return threadPoolExecutor
    }

    @Provides
    fun provideHandler(todosLocalDataSource: TodosLocalDataSource): Handler {
        val mainThreadHandler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())
        return mainThreadHandler
    }

    @Provides
    fun provideTodosRepository(
        todosRemoteDataSource: TodosRemoteDataSource, todosLocalDataSource: TodosLocalDataSource,
        todoPagingSource: TodoPagingSource,
        executor: Executor,
        mainThreadHandler: Handler,
    ): TodosRepository {
        return TodosRepository(
//            todosRemoteDataSource = todosRemoteDataSource,
            todosLocalDataSource = todosLocalDataSource,
//            todoPagingSource = todoPagingSource,
//            dataStore,
//            userPreferencesProtoStore = userPreferencesStore,
//            executor = executor,
//            resultHandler = mainThreadHandler,
        )
    }

    @Provides
    fun bindTodosRepository(
        todosRepository: TodosRepository
    ): AbstractTodosRepository {
        return todosRepository;
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Provides
    fun provideTodoDao(appDatabase: AppDatabase): TodoDao {
        return appDatabase.todoDao()
    }
}