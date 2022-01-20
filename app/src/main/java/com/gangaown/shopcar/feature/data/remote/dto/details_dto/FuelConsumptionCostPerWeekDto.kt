package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.FuelConsumptionCostPerWeek

data class FuelConsumptionCostPerWeekDto(
    val centAmount: Double?,
    val currencyCode: String?,
    val value: Double?
){
    fun toFuelConsumptionCostPerWeek(): FuelConsumptionCostPerWeek{
        return FuelConsumptionCostPerWeek(
            centAmount = centAmount,
            currencyCode = currencyCode,
            value = value
        )
    }
}