package com.example.gdc_kmm_workshop.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdc_kmm_workshop.GdgLogger
import com.example.gdc_kmm_workshop.models.ResModel
import com.example.gdc_kmm_workshop.repositorys.RestaurantRespository
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {
    private val TAG = RestaurantViewModel::class.simpleName ?: ""

    private val restaurantData = MutableLiveData<ResModel>()

    /** 即時性的 LiveData 物件
     *
     * 先來介紹 observable
     * 當value發生改變時發出 callback 通知
     * 只有View的生命週期在前景（foreground）時才發出通知
     *
     * LiveData最強大的地方在於 lifecycle-aware 特性
     * 當LiveData的value發生改變時，若 View 在前景便會直接發送
     * 而View在背景的話，value將會被保留（hold）住，直到回到前景時才發送。
     * 此外，當View被destroy時，LiveData 也會自動停止 observe 行為，避免造成memory-leak。*/
    val feedList: LiveData<ResModel>
        get() = restaurantData

    private val restaurantRespository = RestaurantRespository()

    fun getData() {
        viewModelScope.launch {
            kotlin.runCatching {
                restaurantRespository.getRestaurants()
            }.onSuccess { ResModel ->
                restaurantData.value = ResModel
                GdgLogger.i(TAG, ResModel.toString())
            }.onFailure {
                GdgLogger.e(TAG, it.toString())
            }
        }
    }
}