package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.domain.model.Hp

data class HpDto(
    val centAmount: Int?,
    val currencyCode: String?,
    val value: Int
){
    fun toHp(): Hp{
        return Hp(centAmount, currencyCode, value)
    }
}