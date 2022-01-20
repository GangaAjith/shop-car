package com.gangaown.shopcar.feature.domain.repository

import com.gangaown.shopcar.feature.domain.model.VehicleBasicInfo
import com.gangaown.shopcar.feature.domain.model.VehicleDetails
import com.gangaown.shopcar.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

     fun fetchAllVehicleDetails(): Flow<Resource<List<VehicleBasicInfo>>>
     fun fetchVehicleDetailsInfo():Flow<Resource<VehicleDetails>>
}