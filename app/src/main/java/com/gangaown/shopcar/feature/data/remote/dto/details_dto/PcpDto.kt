package com.gangaown.shopcar.feature.data.remote.dto.details_dto

import com.gangaown.shopcar.feature.domain.model.Pcp

data class PcpDto(
    val centAmount: Int?,
    val currencyCode: String?,
    val value: Int?
){
    fun toPcp():Pcp{
        return Pcp(
            centAmount = centAmount,
            currencyCode = currencyCode,
            value = value
        )
    }
}