package com.timkhakimov.coordinatestableandgraph.di

import com.google.gson.Gson
import com.timkhakimov.coordinatestableandgraph.BuildConfig
import com.timkhakimov.coordinatestableandgraph.data.rest.PointsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providePointsService(retrofit: Retrofit): PointsService {
        return retrofit.create(PointsService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    private companion object {
        const val BASE_URL = "https://hr-challenge.interactivestandard.com"
        const val TIMEOUT = 20L
    }
}