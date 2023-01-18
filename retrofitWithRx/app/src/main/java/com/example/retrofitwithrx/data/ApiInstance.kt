package com.example.retrofitwithrx.data

import com.example.retrofitwithrx.model.DataModelItem
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiInstance {
    private val Base_Url = "https://jsonplaceholder.typicode.com/"
    private val interceptor = kotlin.run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    private val builder = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(Base_Url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiData::class.java)

    fun getData(): Observable<List<DataModelItem>> {
        return builder.getData()
    }


}