package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.common.BaseFragment
import com.udacity.shoestore.common.addFilledTextWatcher
import com.udacity.shoestore.common.isEverythingFilled
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoesListViewModel

class ShoeDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentShoeDetailsBinding

    private val viewModel: ShoesListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.shoe = Shoe("", null, "", "")

        addFilledTextWatchers()
        initClickListeners()

        return binding.root
    }

    private fun addFilledTextWatchers() {
        with(binding) {
            tilShoeName.addFilledTextWatcher()
            tilCompany.addFilledTextWatcher()
            tilShoeSize.addFilledTextWatcher()
            tilDescription.addFilledTextWatcher()
        }
    }

    private fun initClickListeners() {
        with(binding) {
            btnCancel.setOnClickListener {
                navController.navigateUp()
            }

            btnSave.setOnClickListener {
                if (isEverythingFilled(
                        listOf(
                            tilShoeName,
                            tilCompany,
                            tilShoeSize,
                            tilDescription
                        )
                    )
                ) {
                    viewModel.addShoe(binding.shoe)
                    navController.navigateUp()
                }
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_sign_out).isVisible = false
    }
}
