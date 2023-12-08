package com.raul.axpe_prueba_tecnica.data

import UserDataResponse


import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("/")
    suspend fun getUsers(@Query("page") page : Int = 1, @Query("results") results: Int = 20, @Query("seed") seed: String = "testSeed" ) : UserDataResponse
}