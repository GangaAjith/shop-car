package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.VerifiedFeature

data class VerifiedFeatureDto(
    val displayLabel: String?,
    val displayValue: Boolean?,
    val identifier: String?,
    val location: String?
){
    fun toVerifiedFeature():VerifiedFeature{
        return VerifiedFeature(
            displayLabel = displayLabel,
            displayValue = displayValue,
            identifier = identifier,
            location = location
        )
    }
}