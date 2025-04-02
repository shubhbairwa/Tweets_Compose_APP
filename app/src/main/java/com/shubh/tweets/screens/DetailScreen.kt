package com.shubh.tweets.screens


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shubh.tweets.viewmodels.CategoryViewModel
import com.shubh.tweets.viewmodels.DetailViewModel

private const val TAG = "DetailScreen"

@Composable
fun DetailScreen() {
    val detailViewModel: DetailViewModel = hiltViewModel()
    val tweets =
        detailViewModel.tweets.collectAsState() //to get value as state for our views we use collectAsState()

    Log.e(TAG, tweets.value.toString())
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "List",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            style = (MaterialTheme.typography.titleLarge),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            fontStyle = FontStyle.Italic
        )
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,

            ) {
            items(tweets.value) {
                TweetListItem(tweet = it.tweet)
            }
        }
    }



}


@Composable
fun TweetListItem(tweet: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(
            text = tweet,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium, color = Color.Blue
        )
    }
}