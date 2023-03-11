package com.rewardz.quantumwallet.ui.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rewardz.quantumwallet.data.db.WalletDatabase
import com.rewardz.quantumwallet.data.repository.UserRepositoryImpl
import com.rewardz.quantumwallet.databinding.ActivityMainBinding
import com.rewardz.quantumwallet.ui.base.factories.UserViewModelFactory
import com.rewardz.quantumwallet.ui.main.viewmodels.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val userDao = WalletDatabase.getInstance(applicationContext).userDao()
        val userRepositoryImpl = UserRepositoryImpl(userDao)
        val userViewModelFactory = UserViewModelFactory(userRepositoryImpl)
        mViewModel = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]
    }
}