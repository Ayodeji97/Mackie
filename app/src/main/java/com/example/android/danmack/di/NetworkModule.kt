package com.example.android.danmack.di

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.example.android.danmack.network.SongApiService
import com.example.android.danmack.utils.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
/**
 * Module for network
 * */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Telling dagger how to create object of classes that we need
     * */

    /**
     * Gson
     * */
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()

    }


        @Singleton
        @Provides
        fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))


        }


        @Singleton
        @Provides
        fun provideSongApiService(retrofit: Retrofit.Builder): SongApiService {
            return retrofit
                .build()
                .create(SongApiService::class.java)

        }


    @Singleton
    @Provides
    fun provideContext (application: Application) : Context = application.applicationContext

    }




