package com.nhvn.todoandroidnative.ui.elements.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.work.*
import com.nhvn.todoandroidnative.ui.elements.theme.TodoAndroidNativeTheme
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel
import com.nhvn.todoandroidnative.data.work.UploadWorker
import java.util.concurrent.TimeUnit

@ExperimentalMaterial3Api
@Composable
fun DemoScreen(todoViewModel: TodoViewModel) {
    TodoAndroidNativeTheme() {
        Scaffold(
            topBar = {
                Column() {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Demo") },
                        navigationIcon = {
                        },
                        actions = {
                            Text(text = "Pref")
                        }
                    )

                }
            },

            ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                Button(onClick = {
                    val uploadWorkRequest: WorkRequest =
                        OneTimeWorkRequestBuilder<UploadWorker>()
                            .build()

                    WorkManager
                        .getInstance()
                        .enqueue(uploadWorkRequest)
                }) {
                    Text(text = "OneTimeWorkRequest")
                }

                Button(onClick = {
                    val uploadWorkRequest: WorkRequest =
                        PeriodicWorkRequestBuilder<UploadWorker>(15, TimeUnit.MINUTES)
                            // Additional configuration
                            .build()
                    WorkManager
                        .getInstance()
                        .enqueue(uploadWorkRequest)
                }) {
                    Text(text = "Schedule periodic work")
                }

                Button(onClick = {
                    val uploadWorkRequest: WorkRequest =
                        OneTimeWorkRequestBuilder<UploadWorker>()
                            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                            .build()

                    WorkManager
                        .getInstance()
                        .enqueue(uploadWorkRequest)

                }) {
                    Text(text = "setExpedited")
                }

                Button(onClick = {
                    todoViewModel.doAWorkChain()
                }) {
                    Text(text = "Work chain")
                }
            }
        }
    }
}