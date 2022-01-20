package com.gangaown.shopcar.feature.domain.model


data class VehicleDetails(
    val consumption: List<Consumption>?,
    val createdAt: String?,
    val displayVariant: String?,
    val fuelType: FuelType?,
    val id: String?,
    val imageGallery: List<ImageGallery>?,
    val images: Images?,
    val keyFeatures: List<KeyFeature>?,
    val make: String?,
    val makeIdentifier: String?,
    val manufacturerFeatures: List<Any>?,
    val manufacturerSpecs: List<ManufacturerSpec>?,
    val mileage: Int?,
    val model: String?,
    val modelIdentifier: String?,
    val modelYear: Int?,
    val pricing: Pricing?,
    val registrationYear: Int?,
    val runningCosts: RunningCosts?,
    val serviceHistory: List<ServiceHistory>?,
    val state: String?,
    val summaryAttributes: List<SummaryAttribute>?,
    val verifiedFeatures: List<VerifiedFeature>?,
    val vrm: String?
)
