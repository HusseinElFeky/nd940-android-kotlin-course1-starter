package com.udacity.shoestore.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.udacity.shoestore.ui.MainActivity

open class BaseFragment : Fragment() {

    private lateinit var context: MainActivity

    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context = activity as MainActivity
        navController = context.getNavController()
    }
}
