package vick.tech.pagingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import vick.tech.pagingapp.domain.model.Quote

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey
    val author: String,
    val id: Int,
    val quote: String
) {
    fun toQuote() = Quote(
        author, id, quote
    )
}
