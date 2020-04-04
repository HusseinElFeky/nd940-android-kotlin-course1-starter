package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.common.BaseFragment
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : BaseFragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instructions,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.handler = this

        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_sign_out).isVisible = false
    }

    fun navigateToShoesList() {
        navController.navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoesListFragment())
    }
}
