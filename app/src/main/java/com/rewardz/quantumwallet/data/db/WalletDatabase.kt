package com.rewardz.quantumwallet.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rewardz.quantumwallet.data.db.DBConstants.Companion.USER_DATABASE_NAME
import com.rewardz.quantumwallet.data.model.User

@Database(entities = [User::class], version = 1)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var INSTANCE: WalletDatabase? = null

        fun getInstance(context: Context): WalletDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WalletDatabase::class.java,
                        USER_DATABASE_NAME
                    ).build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}