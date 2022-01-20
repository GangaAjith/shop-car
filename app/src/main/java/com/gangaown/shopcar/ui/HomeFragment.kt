package com.gangaown.shopcar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gangaown.shopcar.R
import com.gangaown.shopcar.core.util.UIEvent
import com.gangaown.shopcar.feature.presentation.adapters.VehicleListViewAdapter
import com.gangaown.shopcar.databinding.FragmentHomeBinding
import com.gangaown.shopcar.feature.presentation.HomeViewModel
import com.gangaown.shopcar.feature.presentation.HomeViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment:Fragment(R.layout.fragment_home), KodeinAware{

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val TAG = "HomeFragment"

    override val kodein: Kodein by kodein()
    private val homeViewModelFactory: HomeViewModelFactory by instance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentHomeBinding.inflate(inflater, container, false).also { binding = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)
        val adapter = VehicleListViewAdapter(listOf())
        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = adapter

        homeViewModel.fetchAllVehicleDetails()

        lifecycleScope.launch {
            homeViewModel.eventFlow.collectLatest{ uiEvent ->
                when(uiEvent) {
                    is UIEvent.ShowSnackBar -> {
                        Snackbar.make(
                            view,
                            uiEvent.message,
                            Snackbar.LENGTH_SHORT
                        ).show()
                        binding.ivRefresh.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.INVISIBLE
                    }

                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.vehiclesData.collect { state ->

                    val basicInfo = state.basicInfo
                    if(basicInfo.isNotEmpty()){
                        binding.ivRefresh.visibility = View.INVISIBLE
                        binding.progressBar.visibility = View.INVISIBLE

                        adapter.vehicleList = basicInfo
                        adapter.notifyDataSetChanged()
                    }
                    if(state.isLoading){
                        binding.progressBar.visibility = View.VISIBLE
                        binding.ivRefresh.visibility = View.INVISIBLE
                    }
                }
            }
        }

        binding.ivRefresh.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.ivRefresh.visibility = View.INVISIBLE
            homeViewModel.fetchAllVehicleDetails()
        }
    }

}