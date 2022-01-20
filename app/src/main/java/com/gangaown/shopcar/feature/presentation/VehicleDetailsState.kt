package com.gangaown.shopcar.feature.presentation

import com.gangaown.shopcar.feature.domain.model.VehicleDetails


data class VehicleDetailsState(var vehicleDetails:VehicleDetails? = null, var isLoading:Boolean = false)
