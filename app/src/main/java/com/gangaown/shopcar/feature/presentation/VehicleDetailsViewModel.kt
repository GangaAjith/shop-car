package com.gangaown.shopcar.feature.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gangaown.shopcar.core.util.Resource
import com.gangaown.shopcar.core.util.UIEvent
import com.gangaown.shopcar.feature.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class VehicleDetailsViewModel(private val repository: VehicleRepository):ViewModel() {

    private val _vehicleDetailsDataState    = MutableStateFlow(VehicleDetailsState())
    val vehicleDetailsDataState = _vehicleDetailsDataState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow= _eventFlow.asSharedFlow()

    private val TAG = "VehicleDetailsViewModel"


    fun fetchVehicleDetailsInfo() = viewModelScope.launch {
        repository.fetchVehicleDetailsInfo()
            .onEach { result ->
                when(result){
                    is Resource.Success ->{
                        _vehicleDetailsDataState.value = vehicleDetailsDataState.value.copy(
                            vehicleDetails = result.data, isLoading = false)
                        Log.d(TAG, result.data.toString())
                    }
                    is Resource.Error ->{
                        _eventFlow.emit(UIEvent.ShowSnackBar(message = result.message?:"Unknown Error!"))
                    }
                    is Resource.Loading ->{
                        _vehicleDetailsDataState.value = vehicleDetailsDataState.value.copy(
                            vehicleDetails = result.data, isLoading = true)

                    }
                }
            }.launchIn(this)
    }

}