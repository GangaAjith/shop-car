package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.domain.model.Lowest

data class LowestDto(
    val centAmount: Int?,
    val currencyCode: String?,
    val type: String?,
    val value: Int?
){
    fun toLowest():Lowest{
        return Lowest(
            centAmount = centAmount,
            currencyCode = currencyCode,
            type = type,
            value = value
        )
    }
}