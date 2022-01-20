package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.ManufacturerSpec

data class ManufacturerSpecDto(
    val displayLabel: String?,
    val displayValue: List<DisplayValueDto>?,
    val identifier: String?
){
    fun toManufacturerSpec(): ManufacturerSpec {
        return ManufacturerSpec(
            displayLabel = displayLabel,
            displayValue = displayValue?.map { it.toDisplayValue()},
            identifier = identifier
        )
    }
}