package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.VehicleTaxPerYear

data class VehicleTaxPerYearDto(
    val centAmount: Int?,
    val currencyCode: String?,
    val value: Int?
){
    fun toVehicleTaxPerYear():VehicleTaxPerYear{
        return VehicleTaxPerYear(
            centAmount = centAmount,
            currencyCode = currencyCode,
            value = value
        )
    }
}