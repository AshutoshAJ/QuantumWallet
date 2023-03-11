package com.rewardz.quantumwallet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.rewardz.quantumwallet.data.db.DBConstants

@Entity(tableName = DBConstants.USER_TABLE_NAME)
data class User(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("name")
    var name: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("phone_number")
    var phoneNumber: String

)
