package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.domain.model.Pricing

data class PricingDto(
    val fullPrice: FullPriceDto?,
    val pcmPrice: PcmPriceDto?,
    val subscriptionPrice: SubscriptionPriceDto?
){
    fun toPricing():Pricing{
        return Pricing(
            fullPrice = fullPrice?.toFullPrice(),
            pcmPrice = pcmPrice?.toPcmPrice(),
            subscriptionPrice = subscriptionPrice?.toSubscriptionPrice()
        )
    }
}