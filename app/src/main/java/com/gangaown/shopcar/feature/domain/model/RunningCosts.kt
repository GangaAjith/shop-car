package com.gangaown.shopcar.feature.domain.model


data class RunningCosts(
    val fuelConsumptionCostPerWeek: FuelConsumptionCostPerWeek?,
    val fuelConsumptionMpg: Double?,
    val insuranceCostPerYear: InsuranceCostPerYear?,
    val insuranceGroup: Int?,
    val vehicleTaxPerYear: VehicleTaxPerYear?
)
