package vick.tech.pagingapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vick.tech.pagingapp.data.repo.QuotesRepositoryImpl
import vick.tech.pagingapp.domain.repo.QuotesRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindQuoteRepository(quotesRepositoryImpl: QuotesRepositoryImpl): QuotesRepository
}