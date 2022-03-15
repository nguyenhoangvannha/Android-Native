package com.nhvn.todoandroidnative.data.datasources.paging

import androidx.paging.*
import com.nhvn.todoandroidnative.data.datasources.TodosLocalDataSource
import com.nhvn.todoandroidnative.data.datasources.models.Todo

class TodoPagingSource(
    private val dataSource: TodosLocalDataSource
) : PagingSource<Int, Todo>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Todo> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = dataSource.getTodosByPage(limit = 7, offset = ((nextPageNumber - 1) * 7))
            return LoadResult.Page<Int, Todo>(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).

            return LoadResult.Error(e);
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Todo>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}