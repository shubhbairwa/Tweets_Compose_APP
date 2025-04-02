package com.shubh.tweets.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shubh.tweets.R
import com.shubh.tweets.viewmodels.CategoryViewModel

@Composable
fun CategoryScreens(onClick: (category: String) -> Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories =
        categoryViewModel.categories.collectAsState() //to get value as state for our views we use collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround

    ) {
        items(categories.value.distinct()) {
            CategoryItem(category = it,onClick)
        }

    }
}


@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier

            .size(160.dp)

            .padding(16.dp)
            .clickable {
                onClick(category)
            }
            .clip(RoundedCornerShape(4.dp))
            .paint(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop
            )
            .border(
                1.dp, Color(0xFF9D9D9D)
            ), contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = category,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = (MaterialTheme.typography.bodyMedium), modifier = Modifier.padding(8.dp),
            fontStyle = FontStyle.Normal
        )
    }
}