package com.rewardz.quantumwallet.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rewardz.quantumwallet.data.model.User
import com.rewardz.quantumwallet.data.repository.UserRepositoryImpl
import com.rewardz.quantumwallet.ui.base.resources.Resource
import kotlinx.coroutines.launch

class UserViewModel(
    private var repository: UserRepositoryImpl
): ViewModel() {

    companion object {
        const val TAG = "UserViewModel"
    }

    private val _addUser = MutableLiveData<Resource<Long>>()
    val addUser: LiveData<Resource<Long>> = _addUser

    private val _verifyUserLogin = MutableLiveData<Resource<User>>()
    val verifyUserLogin: LiveData<Resource<User>> = _verifyUserLogin

    fun addUserData(user: User) {
        Log.d(TAG, user.toString())
        viewModelScope.launch {
            try {
                val id = repository.addUser(user)
                Log.d(TAG, "ID $id")
                _addUser.postValue(Resource.Success(id))
            } catch (exception: Exception) {
                _addUser.postValue(Resource.Error("${exception.message}"))
                Log.e(TAG, "Error Occurred! ${exception.message}")
            }
        }
    }

    fun verifyUserLoginData(phoneNumber: String, password: String) {
        viewModelScope.launch {
            try {
                val user = repository.verifyUserLogin(phoneNumber, password)
                if (user != null) {
                    Log.d(TAG, user.toString())
                    _verifyUserLogin.postValue(Resource.Success(user))
                } else {
                    _verifyUserLogin.postValue(Resource.Error("Login Failed! Invalid Credentials"))
                }
            } catch (exception: Exception) {
                _verifyUserLogin.postValue(Resource.Error("Error Occurred! ${exception.message}"))
                Log.e(TAG, "Error Occurred! ${exception.message}")
            }
        }
    }
}