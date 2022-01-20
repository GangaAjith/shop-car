package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.domain.model.FullPrice

data class FullPriceDto(
    val centAmount: Int?,
    val currencyCode: String?,
    val value: Int?
){
    fun toFullPrice():FullPrice{
        return FullPrice(
            centAmount = centAmount,
            currencyCode = currencyCode,
            value = value
        )
    }
}