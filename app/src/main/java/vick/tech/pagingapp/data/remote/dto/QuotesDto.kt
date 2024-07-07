package vick.tech.pagingapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class QuotesDto(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("quotes")
    val quotes: List<QuoteDto>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)