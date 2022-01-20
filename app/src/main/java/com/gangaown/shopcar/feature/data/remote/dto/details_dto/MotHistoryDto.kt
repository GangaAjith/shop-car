package com.gangaown.shopcar.feature.data.remote.dto.details_dto

data class MotHistoryDto(
    val advisories: String?,
    val expiryDate: String?,
    val failures: Any?,
    val hasPassed: Boolean?,
    val mileage: Int?,
    val minors: Any?,
    val testDate: String?
)