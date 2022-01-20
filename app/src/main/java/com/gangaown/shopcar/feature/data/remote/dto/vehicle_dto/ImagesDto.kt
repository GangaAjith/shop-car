package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.domain.model.Images

data class ImagesDto(
    val main: MainDto?
){
    fun toImages(): Images {
        return Images(
            main = main!!.toMain())
    }
}