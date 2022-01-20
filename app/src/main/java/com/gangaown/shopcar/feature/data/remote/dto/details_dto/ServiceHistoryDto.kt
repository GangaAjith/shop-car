package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.ServiceHistory

data class ServiceHistoryDto(
    val date: String?,
    val franchiseName: String?,
    val franchiseType: String?,
    val odometer: OdometerDto?,
    val source: String?
){
    fun toServiceHistory():ServiceHistory{
        return ServiceHistory(
            date = date
        )
    }
}