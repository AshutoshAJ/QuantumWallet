package com.rewardz.quantumwallet.data.repository

import com.rewardz.quantumwallet.data.model.User

interface UserRepository {

    suspend fun addUser(user: User): Long

    suspend fun verifyUserLogin(phoneNumber: String, password: String): User

}