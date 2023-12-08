package com.raul.axpe_prueba_tecnica.data


import UserDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(
    private val api: RandomUserApi
) : RandomUserRepository {

    override suspend fun getUsers(page: Int, results: Int): Flow<UserDataResponse> = flow {
        val data = api.getUsers(page,results)
        emit(data)
    }}