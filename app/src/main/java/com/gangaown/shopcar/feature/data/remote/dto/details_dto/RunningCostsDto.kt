package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.RunningCosts

data class RunningCostsDto(
    val fuelConsumptionCostPerWeek: FuelConsumptionCostPerWeekDto?,
    val fuelConsumptionMpg: Double?,
    val insuranceCostPerYear: InsuranceCostPerYearDto?,
    val insuranceGroup: Int?,
    val vehicleTaxPerYear: VehicleTaxPerYearDto?
){
    fun toRunningCost():RunningCosts{
        return RunningCosts(
            fuelConsumptionCostPerWeek = fuelConsumptionCostPerWeek?.toFuelConsumptionCostPerWeek(),
            fuelConsumptionMpg = fuelConsumptionMpg,
            insuranceCostPerYear =  insuranceCostPerYear?.toInsuranceCostPerYear(),
            insuranceGroup = insuranceGroup,
            vehicleTaxPerYear = vehicleTaxPerYear?.toVehicleTaxPerYear()
        )
    }
}