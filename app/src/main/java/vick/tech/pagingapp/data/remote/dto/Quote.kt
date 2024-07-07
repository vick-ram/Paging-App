package vick.tech.pagingapp.data.remote.dto


import com.google.gson.annotations.SerializedName
import vick.tech.pagingapp.data.local.entity.QuoteEntity

data class QuoteDto(
    @SerializedName("author")
    val author: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("quote")
    val quote: String
) {
    fun toEntity() = QuoteEntity(
        author = author,
        id = id,
        quote = quote
    )
}