package com.hfad.moviewishlist.api


import com.hfad.moviewishlist.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(
//                GsonBuilder().let {
//                    it.registerTypeAdapter(moviesType, MovieDeserializer())
//                    it.create()
//                }
            ))
            .build()
    }

    val api: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}