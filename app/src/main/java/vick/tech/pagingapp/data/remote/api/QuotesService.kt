package vick.tech.pagingapp.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import vick.tech.pagingapp.data.remote.dto.QuotesDto

interface QuotesService {

    @GET("quotes")
    suspend fun getQuotes(
        @Query("skip") skip: Int? = null,
        @Query("limit") limit: Int? = null
    ) : QuotesDto

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}