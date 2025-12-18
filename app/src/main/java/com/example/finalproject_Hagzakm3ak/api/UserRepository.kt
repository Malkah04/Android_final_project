package com.example.finalproject_Hagzakm3ak.api

import retrofit2.Response

class UserRepository(private val api: ApiService) {
    suspend fun getUsers(): Response<List<User>> = api.getUsers()
}