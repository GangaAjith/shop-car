package com.gangaown.shopcar.feature.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gangaown.shopcar.core.util.Resource
import com.gangaown.shopcar.core.util.UIEvent
import com.gangaown.shopcar.feature.domain.model.VehicleBasicInfo
import com.gangaown.shopcar.feature.domain.repository.VehicleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: VehicleRepository) : ViewModel() {

    private val TAG = "HomeViewModel"
    private val _vehiclesData   = MutableStateFlow(VehicleBasicInfoState())
    val vehiclesData: StateFlow<VehicleBasicInfoState> = _vehiclesData

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow  = _eventFlow.asSharedFlow()


    fun fetchAllVehicleDetails() = viewModelScope.launch {
         repository.fetchAllVehicleDetails()
             .onEach { result ->
                 when (result) {
                     is Resource.Success -> {
                         _vehiclesData.value = vehiclesData.value.copy(
                             basicInfo = result.data?: emptyList(), isLoading = false)
                     }
                     is Resource.Error -> {
                         _eventFlow.emit(
                             UIEvent.ShowSnackBar(
                             result.message?: "Unknown error"
                         ))
                     }
                     is Resource.Loading ->{
                         _vehiclesData.value = vehiclesData.value.copy(
                             basicInfo = result.data?: emptyList(), isLoading = true)
                     }

                 }
             }.launchIn(this)
    }

}
