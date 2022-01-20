package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.domain.model.SubscriptionPrice

data class SubscriptionPriceDto(
    val centAmount: Int?,
    val currencyCode: String?,
    val value: Int?
){
    fun toSubscriptionPrice():SubscriptionPrice{
        return SubscriptionPrice(
            centAmount = centAmount,
            currencyCode = currencyCode,
            value = value)
    }
}