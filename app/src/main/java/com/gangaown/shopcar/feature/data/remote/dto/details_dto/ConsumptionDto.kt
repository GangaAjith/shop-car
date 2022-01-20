package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.Consumption

data class ConsumptionDto(
    val displayLabel: String?,
    val displayValue: String?,
    val identifier: String?
){
    fun toConsumption():Consumption{
        return Consumption(
            displayLabel = displayLabel,
            displayValue = displayValue,
            identifier = identifier)
    }
}