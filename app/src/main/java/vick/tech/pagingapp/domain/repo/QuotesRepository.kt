package vick.tech.pagingapp.domain.repo

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import vick.tech.pagingapp.data.local.entity.QuoteEntity

interface QuotesRepository {
    fun getQuotes() : Flow<PagingData<QuoteEntity>>
}