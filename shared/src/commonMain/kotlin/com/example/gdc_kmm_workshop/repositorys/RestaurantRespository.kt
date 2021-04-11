package com.example.gdc_kmm_workshop.repositorys

import com.example.gdc_kmm_workshop.GdgLogger
import com.example.gdc_kmm_workshop.models.ResModel
import com.example.gdc_kmm_workshop.repositorys.apis.RestaurantApi

class RestaurantRespository {
    private val api = RestaurantApi()

    companion object {
        internal val TAG = RestaurantRespository::class.simpleName ?: ""
    }
    suspend fun getRestaurants(): ResModel {
        api.getRestaurantData().also {
            GdgLogger.i(TAG, it.toString())
            return it
        }
    }
}