package com.nhvn.todoandroidnative.ui.elements.widgets

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun UserInfoList(modifier: Modifier, todoViewModel: TodoViewModel) {
    val userListItems: LazyPagingItems<Todo> =
        todoViewModel.getTodosByPage().collectAsLazyPagingItems()
    print("userListItems $userListItems")
    LazyColumn {
        items(userListItems.itemCount) { index ->
            TodoCard(todo = userListItems[index]!!)
        }
        userListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }
    }
}