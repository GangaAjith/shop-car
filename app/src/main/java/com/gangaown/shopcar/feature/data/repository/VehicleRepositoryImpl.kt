package com.gangaown.shopcar.feature.data.repository

import com.gangaown.shopcar.core.util.Resource
import com.gangaown.shopcar.core.util.SharedInfoUtil.PRODUCT_ID
import com.gangaown.shopcar.feature.data.remote.VehicleApi
import com.gangaown.shopcar.feature.data.remote.dto.vehicle_dto.VehicleBasicInfoDto
import com.gangaown.shopcar.feature.domain.model.VehicleBasicInfo
import com.gangaown.shopcar.feature.domain.model.VehicleDetails
import com.gangaown.shopcar.feature.domain.repository.VehicleRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class VehicleRepositoryImpl(private val apiInstance: VehicleApi):VehicleRepository{

    private val TAG = "VehicleRepository"

    override fun fetchAllVehicleDetails(): Flow<Resource<List<VehicleBasicInfo>>> = flow {
       emit(Resource.Loading())
        try{
            val response = apiInstance.getAllVehicles()
            if(response.isSuccessful){
                response.body()?.let { body ->
                    val vehicles = body.getAsJsonArray("results")
                    val turnsType = object : TypeToken<MutableList<VehicleBasicInfoDto>>() {}.type
                    val vehicleList:List<VehicleBasicInfoDto> = Gson().fromJson(vehicles, turnsType)
                    val vehiclesInfo = vehicleList.map {vehicleBasicInfo ->
                       vehicleBasicInfo.toVehicleBasicInfo()
                    }
                    emit(Resource.Success(vehiclesInfo))

                }?:emit(Resource.Error(
                    data = null, message = "Oops, Something went wrong! "))

            }else{
                emit(Resource.Error(
                    data = null, message = "Oops, Something went wrong! "))
            }

        }catch (e: HttpException){
            emit(Resource.Error(data = null, message = "Oops, Something went wrong! "))
        }catch(e:IOException){
            emit(Resource.Error(data = null,
                message = "Couldn't reach the server, check your network connection!"))
        }

    }

    override fun fetchVehicleDetailsInfo():Flow<Resource<VehicleDetails>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiInstance.getVehicleDetails(PRODUCT_ID)
            if (response.isSuccessful) {
                response.body()?.let {
                    val vehicleDetails = it.toVehicleDetails()
                    emit(Resource.Success(data= vehicleDetails))

                }?:emit(Resource.Error(message = "An unknown Error occurred", data = null))
            }else{
                emit(Resource.Error(message = "An unknown Error occurred", data=null))
            }
        }catch (e:IOException) {
            emit(Resource.Error(message = "Couldn't reach server, Check your network connection", data = null))
        }
    }

}