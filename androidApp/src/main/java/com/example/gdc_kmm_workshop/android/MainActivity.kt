package com.example.gdc_kmm_workshop.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gdc_kmm_workshop.Greeting
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gdc_kmm_workshop.android.UI.SetMainActivityPage
import com.example.gdc_kmm_workshop.models.ResModel
import com.example.gdc_kmm_workshop.repositorys.RestaurantRespository

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent { GetRssFeeds() }
    }

    @Composable
    fun GetRssFeeds() {
        val vm: RestaurantViewModel = viewModel()
        // 監測 RestaurantState 資料是否有變化
        val RestaurantState: State<ResModel?> = vm.feedList.observeAsState()

        vm.getData()

        RestaurantState.let { stateIt ->
            stateIt.value?.let { list ->
                SetMainActivityPage().SetRestaurantList(data = list)
            }
        }
    }
}
