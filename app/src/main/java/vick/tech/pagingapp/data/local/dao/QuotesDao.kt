package vick.tech.pagingapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import vick.tech.pagingapp.data.local.entity.QuoteEntity

@Dao
interface QuotesDao {
    @Transaction
    @Upsert
    suspend fun insertAll(quotesEntity: List<QuoteEntity>)

    @Query("SELECT * FROM quotes")
    fun loadAll() : PagingSource<Int, QuoteEntity>

    @Transaction
    @Query("DELETE FROM quotes")
    suspend fun clearAll()
}