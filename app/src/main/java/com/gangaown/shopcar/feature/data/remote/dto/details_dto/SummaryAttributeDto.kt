package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.SummaryAttribute

data class SummaryAttributeDto(
    val displayLabel: String?,
    val displayValue: String?,
    val identifier: String?
){
    fun toSummaryAttribute():SummaryAttribute{
        return SummaryAttribute(
            displayLabel = displayLabel,
            displayValue = displayValue,
            identifier = identifier
        )
    }
}