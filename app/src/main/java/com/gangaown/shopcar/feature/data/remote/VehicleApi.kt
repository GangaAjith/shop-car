package com.gangaown.shopcar.feature.data.remote

import com.gangaown.shopcar.feature.data.remote.dto.details_dto.VehicleDetailsDto
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleApi {

    @GET("/api/search/")
    suspend fun getAllVehicles():Response<JsonObject>

    @GET("/api/vehicles/{product_id}")
    suspend fun getVehicleDetails(@Path ("product_id") productId:String):Response<VehicleDetailsDto>

}