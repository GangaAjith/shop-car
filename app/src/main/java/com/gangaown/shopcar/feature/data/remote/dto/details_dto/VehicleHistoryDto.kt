package com.gangaown.shopcar.feature.data.remote.dto.details_dto

data class VehicleHistoryDto(
    val motHistory: List<MotHistoryDto>?,
    val numberOfPreviousKeepers: Int?,
    val registrationDate: String?
)