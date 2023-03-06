package com.clint.cars.core.di

import android.content.Context
import androidx.room.Room
import com.clint.cars.BuildConfig
import com.clint.cars.features.cars.CarsDao
import com.clint.cars.features.cars.CarsDatabase
import com.clint.cars.features.cars.CarsRepository
import com.clint.cars.features.cars.NetWork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.npoint.io/").client(createClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun providesCarsDb(@ApplicationContext context: Context): CarsDatabase {
        return Room.databaseBuilder(context, CarsDatabase::class.java, "cars-db").build()
    }

    @Singleton
    @Provides
    fun providesCarsDao(carsDatabase: CarsDatabase): CarsDao {
        return carsDatabase.carsDao()
    }

    @Singleton
    @Provides
    fun providesRepository(dataSource: NetWork): CarsRepository = dataSource
}