package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.InsuranceCostPerYear

data class InsuranceCostPerYearDto(
    val centAmount: Int?,
    val currencyCode: String?,
    val value: Int?
){
    fun toInsuranceCostPerYear() : InsuranceCostPerYear{
        return InsuranceCostPerYear(
            centAmount = centAmount,
            currencyCode = currencyCode,
            value = value
        )
    }
}