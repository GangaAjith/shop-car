package com.gangaown.shopcar

import android.app.Application
import com.gangaown.shopcar.core.util.Constants
import com.gangaown.shopcar.feature.data.remote.VehicleApi
import com.gangaown.shopcar.feature.data.repository.VehicleRepositoryImpl
import com.gangaown.shopcar.feature.presentation.HomeViewModelFactory
import com.gangaown.shopcar.feature.presentation.VehicleDetailsVMFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VehicleApplication:Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@VehicleApplication))

        bind() from singleton { VehicleRepositoryImpl(
             Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VehicleApi::class.java))
        }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { VehicleDetailsVMFactory(instance()) }
    }
}