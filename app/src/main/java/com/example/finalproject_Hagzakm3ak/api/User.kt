package com.example.finalproject_Hagzakm3ak.api

data class User(
    val id: Int,
    val name: String?,
    val username: String?,
    val email: String?,
    val phone: String?,
    val website: String?,
    val company: Company?,
    val address: Address?
)

data class Company(
    val name: String?
)

data class Address(
    val street: String?,
    val city: String?
)