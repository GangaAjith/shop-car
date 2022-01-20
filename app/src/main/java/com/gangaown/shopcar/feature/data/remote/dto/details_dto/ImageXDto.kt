package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.ImageX

data class ImageXDto(
    val large: String?,
    val medium: String?,
    val small: String?
){
    fun toImageX():ImageX{
        return ImageX(
            large = large,
            medium = medium,
            small = small
        )
    }
}