package com.rewardz.quantumwallet.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.rewardz.quantumwallet.R
import com.rewardz.quantumwallet.databinding.FragmentLoginBinding
import com.rewardz.quantumwallet.ui.base.resources.Resource
import com.rewardz.quantumwallet.ui.constants.Constants.Companion.BUNDLE_KEY_USER_NAME
import com.rewardz.quantumwallet.ui.main.viewmodels.UserViewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
        const val TAG = "LoginFragment"
    }

    private lateinit var mBinding: FragmentLoginBinding
    private val mViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.progressBar.visibility = View.GONE
        initListeners()
        subscribeObservables()
    }

    private fun initListeners() {
        // Clearing all items focus on layout touch
        mBinding.root.setOnClickListener {
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(mBinding.root.windowToken, 0)
            mBinding.root.clearFocus()
        }

        // Verify login with credentials
        mBinding.loginBtn.setOnClickListener {
            val pair: Pair<String, String>? = getDetails()
            if (pair != null) {
                mViewModel.verifyUserLoginData(pair.first, pair.second)
            }
        }

        mBinding.registerBtn.setOnClickListener {
            // One way navigation
            findNavController().navigate(
                R.id.signUpFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.nav_graph, true).build()
            )
        }
    }

    private fun getDetails(): Pair<String, String>? {
        val phone = mBinding.userPhoneET.text.toString()
        val password = mBinding.userPasswordET.text.toString()

        if (TextUtils.isEmpty(phone)) {
            mBinding.userPhoneET.error =
                requireActivity().resources.getString(R.string.phone_cannot_be_empty)
            return null
        } else if (phone.length < 10) {
            mBinding.userPhoneET.error =
                requireActivity().resources.getString(R.string.phone_number_cannot_be_less_than_10)
            return null
        } else {
            mBinding.userPhoneET.error = null
        }

        if (TextUtils.isEmpty(password)) {
            mBinding.userPasswordET.error =
                requireActivity().resources.getString(R.string.password_cannot_be_empty)
            return null
        } else {
            mBinding.userPasswordET.error = null
        }

        return Pair(phone, password)
    }

    private fun subscribeObservables() {
        mViewModel.verifyUserLogin.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    mBinding.progressBar.visibility = View.GONE
                    Log.d(TAG, resource.toString())
                    val user = (resource as Resource.Success).data
                    val args = Bundle()
                    args.putString(BUNDLE_KEY_USER_NAME, user.name)
                    Log.d(TAG, "Args $args")
                    findNavController().navigate(
                        R.id.welcomeFragment,
                        args,
                        NavOptions.Builder().setPopUpTo(R.id.nav_graph, true).build()
                    )
                }

                Resource.Status.LOADING -> {
                    mBinding.progressBar.visibility = View.VISIBLE
                }

                Resource.Status.ERROR -> {
                    mBinding.progressBar.visibility = View.GONE
                    val message = (resource as Resource.Error).message
                    Log.d(TAG, message)
                    Toast.makeText(
                        requireActivity().applicationContext,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}