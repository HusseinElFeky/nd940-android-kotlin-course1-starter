package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.common.*
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this

        addFilledTextWatchers()
        initClickListeners()

        return binding.root
    }

    private fun addFilledTextWatchers() {
        with(binding) {
            tilEmail.addFilledTextWatcher()
            tilPassword.addFilledTextWatcher()
        }
    }

    private fun initClickListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                if (isEverythingFilled(listOf(tilEmail, tilPassword))) {
                    PreferencesHelper.isLoggedIn = true
                    navController.navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                }
            }

            tvCreateAccount.setClickableText(
                getString(R.string.create_one_now),
                "${getString(R.string.no_account)} ${getString(
                    R.string.create_one_now
                )}"
            ) {
                PreferencesHelper.isLoggedIn = true
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_sign_out).isVisible = false
    }
}
