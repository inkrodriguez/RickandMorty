package com.inkrodriguez.rickandmortyapi

import com.inkrodriguez.rickandmortyapi.api.myData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("character")
    fun getData(): Call<myData>


}