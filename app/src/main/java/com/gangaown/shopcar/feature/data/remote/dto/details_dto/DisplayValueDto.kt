package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.DisplayValue

data class DisplayValueDto(
    val displayLabel: String?,
    val displayValue: String?,
    val identifier: String?
){
    fun toDisplayValue(): DisplayValue {
        return DisplayValue(
            displayLabel = displayLabel,
            displayValue = displayValue,
            identifier = identifier
        )
    }
}