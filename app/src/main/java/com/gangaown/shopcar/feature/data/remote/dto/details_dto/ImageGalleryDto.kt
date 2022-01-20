package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.ImageGallery

data class ImageGalleryDto(
    val large: String?,
    val medium: String?,
    val small: String?
){
    fun toImageGallery(): ImageGallery {
        return ImageGallery(large = large,
        medium = medium,
        small = small)
    }
}