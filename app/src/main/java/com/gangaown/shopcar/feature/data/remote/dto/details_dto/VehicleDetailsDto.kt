package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto.FuelTypeDto
import com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto.ImagesDto
import com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto.OdometerReadingDto
import com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto.PricingDto
import com.gangaown.shopcar.feature.domain.model.VehicleDetails

data class VehicleDetailsDto(
    val closeDoors360: List<CloseDoors360Dto>?,
    val consumption: List<ConsumptionDto>?,
    val createdAt: String?,
    val displayVariant: String?,
    val featuresGallery: List<FeaturesGalleryDto>?,
    val fuelType: FuelTypeDto?,
    val id: String?,
    val imageGallery: List<ImageGalleryDto>?,
    val images: ImagesDto?,
    val imperfectionsGallery: List<ImperfectionsGalleryDto>?,
    val internal360: String?,
    val isForPurchase: Boolean?,
    val isForSubscription: Boolean?,
    val isPromoted: Boolean?,
    val isSold: Boolean?,
    val keyFeatures: List<KeyFeatureDto>?,
    val make: String?,
    val makeIdentifier: String?,
    val manufacturerFeatures: List<Any>?,
    val manufacturerSpecs: List<ManufacturerSpecDto>?,
    val mileage: Int?,
    val model: String?,
    val modelIdentifier: String?,
    val modelYear: Int?,
    val odometerReading: OdometerReadingDto?,
    val openDoors360: List<OpenDoors360Dto>?,
    val pricing: PricingDto?,
    val registrationYear: Int?,
    val runningCosts: RunningCostsDto?,
    val serviceHistory: List<ServiceHistoryDto>?,
    val serviceHistoryDocumentationFound: Boolean?,
    val state: String?,
    val summaryAttributes: List<SummaryAttributeDto>?,
    val tradingMarket: String?,
    val vehicleHistory: VehicleHistoryDto?,
    val verifiedFeatures: List<VerifiedFeatureDto>?,
    val vrm: String?
){
    fun toVehicleDetails():VehicleDetails {
        return VehicleDetails(
            consumption = consumption?.map { it.toConsumption() },
            createdAt = createdAt,
            displayVariant = displayVariant,
            fuelType = fuelType?.toFuelType(),
            id = id,
            imageGallery = imageGallery?.map { it.toImageGallery() },
            images = images?.toImages(),
            keyFeatures = keyFeatures?.map { it.toKeyFeature() },
            make = make,
            makeIdentifier = makeIdentifier,
            manufacturerFeatures = manufacturerFeatures,
            manufacturerSpecs = manufacturerSpecs?.map { it.toManufacturerSpec() },
            mileage = mileage,
            model = model,
            modelIdentifier = modelIdentifier,
            modelYear = modelYear,
            pricing = pricing?.toPricing(),
            registrationYear = registrationYear,
            runningCosts = runningCosts?.toRunningCost(),
            serviceHistory = serviceHistory?.map { it.toServiceHistory() },
            state = state,
            summaryAttributes = summaryAttributes?.map { it.toSummaryAttribute() },
            verifiedFeatures = verifiedFeatures?.map { it.toVerifiedFeature() },
            vrm = vrm
        )
    }
}