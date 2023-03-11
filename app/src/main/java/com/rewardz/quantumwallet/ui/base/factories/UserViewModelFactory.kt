package com.rewardz.quantumwallet.ui.base.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rewardz.quantumwallet.data.db.UserDao
import com.rewardz.quantumwallet.data.repository.UserRepositoryImpl
import com.rewardz.quantumwallet.ui.main.viewmodels.UserViewModel

class UserViewModelFactory(
    private var userRepositoryImpl: UserRepositoryImpl
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepositoryImpl) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
    }

}