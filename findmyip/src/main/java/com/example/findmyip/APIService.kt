package com.example.findmyip

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {
    @GET("/json/")
    suspend fun getInfo():IPInfo
    companion object{
        private const val BASE_URL="https://ipapi.co/json/"
        fun create(): APIService{
            val retrofit= Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(APIService::class.java)
        }
    }
}