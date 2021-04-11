package com.example.gdc_kmm_workshop.android.UI

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.gdc_kmm_workshop.models.ResModel
import com.example.gdc_kmm_workshop.models.ResModelData
import com.google.accompanist.coil.CoilImage

class SetMainActivityPage() {

    @Composable
    fun SetRestaurantList(data: ResModel) {
        LazyColumn() {
            items(data.data) { resModelData ->
                RestaurantItemCard(item = resModelData)
            }
        }
    }

    @Composable
    fun RestaurantItemCard(item: ResModelData) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(bottom = 8.dp)
                ) {
                    CoilImage(
                        data = "https://i.imgur.com/ubWh7YM.jpg",
                        contentDescription = item.name + "\n" + "最低\$" + item.lowest_price + " 外送",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Inside
                    )
                }

                Text(item.name + "\n" + "最低\$" + item.lowest_price + " 外送")
                Text(item.description)
                Text(item.address)
            }
        }
    }
}