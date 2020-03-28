package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.udacity.shoestore.R
import com.udacity.shoestore.common.BaseFragment
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.viewmodels.ShoesListViewModel

class ShoesListFragment : BaseFragment() {

    private lateinit var binding: FragmentShoesListBinding

    private val viewModel: ShoesListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)
        binding.lifecycleOwner = this

        initClickListeners()
        initViewModel()

        return binding.root
    }

    private fun initClickListeners() {
        binding.fabAddShoe.setOnClickListener {
            navController.navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoesDetailsFragment())
        }
    }

    private fun initViewModel() {
        binding.viewModel = viewModel

        viewModel.shoesLiveData.observe(this) {
            binding.llShoesList.removeAllViews()
            for (i in 0 until it.size) {
                val shoeBinding =
                    DataBindingUtil.inflate<ItemShoeBinding>(
                        LayoutInflater.from(activity),
                        R.layout.item_shoe,
                        binding.llShoesList,
                        false
                    )
                shoeBinding.shoe = it[i]
                binding.llShoesList.addView(shoeBinding.root)
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_sign_out).isVisible = true
    }
}
