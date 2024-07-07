package vick.tech.pagingapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import vick.tech.pagingapp.data.local.db.QuotesDb
import vick.tech.pagingapp.data.remote.api.QuotesService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuotesDb {
        return Room.databaseBuilder(
            context = context,
            klass = QuotesDb::class.java,
            name = "quotes.db"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService() : QuotesService {
        return Retrofit.Builder()
            .baseUrl(QuotesService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}