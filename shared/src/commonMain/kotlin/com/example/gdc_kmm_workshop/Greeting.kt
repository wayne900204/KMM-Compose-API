package com.example.gdc_kmm_workshop

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}