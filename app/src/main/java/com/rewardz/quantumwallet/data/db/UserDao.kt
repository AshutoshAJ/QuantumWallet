package com.rewardz.quantumwallet.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rewardz.quantumwallet.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM ${DBConstants.USER_TABLE_NAME} where phoneNumber = :phoneNumber AND password = :password")
    suspend fun verifyUserLogin(phoneNumber: String, password: String): User

}