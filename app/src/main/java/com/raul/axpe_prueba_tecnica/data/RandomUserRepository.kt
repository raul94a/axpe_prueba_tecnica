package com.raul.axpe_prueba_tecnica.data


import UserDataResponse
import kotlinx.coroutines.flow.Flow

interface RandomUserRepository {

    suspend fun getUsers(page: Int, results: Int = 20) : Flow<UserDataResponse>


}