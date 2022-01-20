package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.domain.model.VehicleBasicInfo

data class VehicleBasicInfoDto(
    val createdAt: String?,
    val displayVariant: String?,
    val fuelType: FuelTypeDto?,
    val id: String?,
    val images: ImagesDto?,
    val isForPurchase: Boolean?,
    val isForSubscription: Boolean?,
    val isPromoted: Boolean?,
    val make: String?,
    val mileage: Int?,
    val model: String?,
    val modelYear: Int?,
    val odometerReading: OdometerReadingDto?,
    val pricing: PricingDto?,
    val registrationYear: Int?,
    val tradingMarket: String?,
    val vrm: String?
){
    fun toVehicleBasicInfo():VehicleBasicInfo{
        return VehicleBasicInfo(
            createdAt = createdAt,
            displayVariant = displayVariant,
            fuelType = fuelType?.toFuelType(),
            id = id,
            images = images?.toImages(),
            make = make,
            mileage = mileage,
            model = model,
            modelYear = modelYear,
            pricing =  pricing?.toPricing(),
            registrationYear = registrationYear,
        )
    }
}