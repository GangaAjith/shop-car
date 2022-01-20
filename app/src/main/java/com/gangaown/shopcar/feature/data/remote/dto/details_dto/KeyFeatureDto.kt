package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.KeyFeature

data class KeyFeatureDto(
    val displayValue: String?,
    val identifier: String?
){
    fun toKeyFeature(): KeyFeature {
        return KeyFeature(
            displayValue = displayValue,
            identifier = identifier
        )
    }
}