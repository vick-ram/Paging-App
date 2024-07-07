package vick.tech.pagingapp.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vick.tech.pagingapp.data.local.db.QuotesDb
import vick.tech.pagingapp.data.local.entity.QuoteEntity
import vick.tech.pagingapp.data.remote.api.QuotesService
import vick.tech.pagingapp.domain.repo.QuotesRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class QuotesRepositoryImpl @Inject constructor(
    private val apiService: QuotesService,
    private val database: QuotesDb
) : QuotesRepository {
    override fun getQuotes(): Flow<PagingData<QuoteEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { database.quotesDao().loadAll() },
            remoteMediator = QuotesRemoteMediator(apiService, database)
        ).flow
    }
}