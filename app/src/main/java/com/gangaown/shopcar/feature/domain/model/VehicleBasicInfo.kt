package com.gangaown.shopcar.feature.domain.model


data class VehicleBasicInfo(
    val createdAt: String?,
    val displayVariant: String?,
    val fuelType: FuelType?,
    val id: String?,
    val images: Images?,
    val make: String?,
    val mileage: Int?,
    val model: String?,
    val modelYear: Int?,
    val pricing: Pricing?,
    val registrationYear: Int?,
)
