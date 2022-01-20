package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.Image

data class ImageDto(
    val large: String?,
    val medium: String?,
    val small: String?
){
    fun toImage(): Image {
        return Image(
            large = large,
            medium = medium,
            small = small)
    }


}