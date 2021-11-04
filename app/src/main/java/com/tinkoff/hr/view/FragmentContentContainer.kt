package com.tinkoff.hr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tinkoff.hr.R
import com.tinkoff.hr.databinding.FragmentContentContainerBinding
import com.tinkoff.hr.utils.setupWithNavController

class FragmentContentContainer : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContentContainerBinding.inflate(inflater, container, false)

        binding.bottomNav.setupWithNavController(
            listOf(
                R.navigation.nav_graph_orders,
                R.navigation.nav_graph_employees,
                R.navigation.nav_graph_faq
            ), childFragmentManager, R.id.nav_host_fragment_content,
        ).observe(viewLifecycleOwner) {
            navController = it
        }

        return binding.root
    }
}