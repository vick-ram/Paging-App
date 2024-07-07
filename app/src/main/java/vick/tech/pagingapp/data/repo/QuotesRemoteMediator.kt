@file:OptIn(ExperimentalPagingApi::class)

package vick.tech.pagingapp.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import vick.tech.pagingapp.data.local.db.QuotesDb
import vick.tech.pagingapp.data.local.entity.QuoteEntity
import vick.tech.pagingapp.data.remote.api.QuotesService
import javax.inject.Inject

class QuotesRemoteMediator @Inject constructor(
    private val apiService: QuotesService,
    private val database: QuotesDb
) : RemoteMediator<Int, QuoteEntity>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, QuoteEntity>
    ): MediatorResult {
        val skip = when(loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                lastItem?.id ?: 0
            }
        }
        val response = apiService.getQuotes(
            skip = skip,
            limit = state.config.pageSize
        )
        val entityQuotes = response.quotes.map { it.toEntity() }
        if (loadType == LoadType.REFRESH) {
            database.quotesDao().clearAll()
        }
        database.quotesDao().insertAll(entityQuotes)
        return MediatorResult.Success(endOfPaginationReached = entityQuotes.isEmpty())
    }
}