package com.gangaown.shopcar.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gangaown.shopcar.feature.data.repository.VehicleRepositoryImpl

class VehicleDetailsVMFactory (private val repository: VehicleRepositoryImpl):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VehicleDetailsViewModel(repository) as T
    }
}