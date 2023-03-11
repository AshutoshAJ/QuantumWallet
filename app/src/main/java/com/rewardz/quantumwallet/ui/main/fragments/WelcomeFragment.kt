package com.rewardz.quantumwallet.ui.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rewardz.quantumwallet.R
import com.rewardz.quantumwallet.databinding.FragmentWelcomeBinding
import com.rewardz.quantumwallet.ui.constants.Constants.Companion.BUNDLE_KEY_USER_NAME

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
        const val TAG = "WelcomeFragment"
    }

    private lateinit var mBinding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName = arguments?.getString(BUNDLE_KEY_USER_NAME)
        if (userName != null) {
            Log.d(TAG, userName)
            mBinding.loggedInMessageTV.text = buildString {
                append(requireActivity().resources.getString(R.string.hey))
                    .append(" ")
                append(userName)
                append(requireActivity().resources.getString(R.string.explore_a_new_world))
            }
        } else {
            Log.d(TAG, "Username Null")
        }
    }

}