package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.domain.model.Main

data class MainDto(
    val url: String?
){
    fun toMain(): Main {
        return Main(
            url = url
        )
    }
}