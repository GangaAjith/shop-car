package com.gangaown.shopcar.feature.data.remote.dto.details_dto

data class FeaturesGalleryDto(
    val coordinates: CoordinatesDto?,
    val description: String?,
    val image: ImageDto?,
    val parentImage: ParentImageDto?,
    val title: String?
)