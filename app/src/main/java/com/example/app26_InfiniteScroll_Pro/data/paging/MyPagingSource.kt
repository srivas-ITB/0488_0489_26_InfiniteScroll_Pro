package com.example.app26_InfiniteScroll_Pro.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.app26_InfiniteScroll_Pro.data.entity.MyData
import kotlinx.coroutines.delay

class MyPagingSource : PagingSource<Int, MyData>() {
    override fun getRefreshKey(state: PagingState<Int, MyData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyData> {
        return try {
            val nextPage = params.key ?: 1
            // Simulem la crida a l'API
            delay(1000)
            val response = (1..20).map { MyData("Element #$it (Pàgina $nextPage)") }

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}