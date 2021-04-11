package com.example.gdc_kmm_workshop.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResModelData(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("address")
    val address: String,
    @SerialName("lowest_price")
    val lowest_price: String,
)

@Serializable
data class ResModel(
    @SerialName("success")
    val success: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<ResModelData>
)