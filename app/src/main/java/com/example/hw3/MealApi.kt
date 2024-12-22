package com.example.hw3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("filter.php")
    fun getMealsByCategory(@Query("c") category: String): Call<MealResponse>

    @GET("lookup.php")
    fun getMealById(@Query("i") id: String): Call<MealResponse>
}