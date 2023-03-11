package com.rewardz.quantumwallet.data.repository

import com.rewardz.quantumwallet.data.db.UserDao
import com.rewardz.quantumwallet.data.model.User

class UserRepositoryImpl(
    private var userDao: UserDao
): UserRepository {
    override suspend fun addUser(user: User): Long {
        return userDao.insertUser(user)
    }

    override suspend fun verifyUserLogin(phoneNumber: String, password: String): User {
        return userDao.verifyUserLogin(phoneNumber, password)
    }
}