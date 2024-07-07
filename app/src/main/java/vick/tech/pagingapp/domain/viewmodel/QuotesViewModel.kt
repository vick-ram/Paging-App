package vick.tech.pagingapp.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vick.tech.pagingapp.domain.model.Quote
import vick.tech.pagingapp.domain.repo.QuotesRepository
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val quotesRepository: QuotesRepository
) : ViewModel(){
    val quotes: Flow<PagingData<Quote>> = quotesRepository.getQuotes()
        .map { pagingData ->
            pagingData.map {quoteEntity ->
                quoteEntity.toQuote()
            }
        }.cachedIn(viewModelScope)
}