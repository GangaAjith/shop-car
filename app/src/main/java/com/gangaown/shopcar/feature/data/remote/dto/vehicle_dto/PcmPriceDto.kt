package com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto

import com.gangaown.shopcar.feature.data.remote.dto.details_dto.PcpDto
import com.gangaown.shopcar.feature.domain.model.PcmPrice

data class PcmPriceDto(
    val hp: HpDto?,
    val lowest: LowestDto?,
    val pcp: PcpDto?
){
    fun toPcmPrice():PcmPrice{
        return PcmPrice(
            hp = hp?.toHp(),
            lowest = lowest?.toLowest(),
            pcp = pcp?.toPcp()

        )
    }
}