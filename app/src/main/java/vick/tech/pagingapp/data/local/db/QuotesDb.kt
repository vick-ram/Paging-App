package vick.tech.pagingapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import vick.tech.pagingapp.data.local.dao.QuotesDao
import vick.tech.pagingapp.data.local.entity.QuoteEntity

@Database(
    entities = [QuoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuotesDb: RoomDatabase() {
    abstract fun quotesDao(): QuotesDao
}