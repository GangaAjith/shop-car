package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.domain.model.FuelType

data class FuelTypeDto(
    val description: String?,
    val isEcoFriendly: Boolean?,
    val isPlugin: Boolean?
){
    fun toFuelType(): FuelType {
        return FuelType(description = description,
        isEcoFriendly = isEcoFriendly,
        isPlugin = isPlugin)
    }
}