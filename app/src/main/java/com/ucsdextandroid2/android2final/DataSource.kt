package com.ucsdextandroid2.android2final

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Collections.emptyList


class DataSource private constructor() {

    private var baseOkHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private val triviaApi: TriviaApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://opentdb.com/")
        .client(baseOkHttpClient)
        .build()
        .create(TriviaApi::class.java)

    fun search(searchCategory: Int, searchDifficulty: String?,  callback: Callback<List<Question>>) {
        triviaApi.searchTrivia(searchCategory, searchDifficulty).enqueue(object : retrofit2.Callback<TriviaSearchResults> {
            override fun onResponse(call: Call<TriviaSearchResults>, response: Response<TriviaSearchResults>) {
                callback.onDataFetched(if (response.isSuccessful) response.body()?.questions.orEmpty() else emptyList())
            }

            override fun onFailure(call: Call<TriviaSearchResults>, t: Throwable) {
                callback.onDataFetched(emptyList<Question>())
            }
        })
    }

    interface Callback<T> {
        fun onDataFetched(data: T)
    }

    interface TriviaApi {
        @GET("api.php?amount=10&type=multiple")
        fun searchTrivia(
            @Query("category") category: Int?,
            @Query("difficulty") difficulty: String?
        ): Call<TriviaSearchResults>
    }

    companion object {

        private var instance: DataSource? = null

        @JvmStatic
        fun getInstance(): DataSource {
            if (instance == null)
                instance = DataSource()

            return instance!!
        }
    }
}