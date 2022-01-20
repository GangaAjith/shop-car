package com.gangaown.shopcar.feature.presentation

import com.gangaown.shopcar.feature.domain.model.VehicleBasicInfo

data class VehicleBasicInfoState( val basicInfo: List<VehicleBasicInfo> = emptyList(),
                                  val isLoading:Boolean = false)
