package com.shubh.tweets.screens

import android.annotation.SuppressLint
import android.widget.ProgressBar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shubh.tweets.R
import com.shubh.tweets.R.color.purple_500
import com.shubh.tweets.viewmodels.CategoryViewModel

@Composable
fun CategoryScreens(onClick: (category: String) -> Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories =
        categoryViewModel.categories.collectAsState() //to get value as state for our views we use collectAsState()

    if (categories.value.isEmpty()) {
        showLoader()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround

        ) {
            items(categories.value.distinct()) {
                CategoryItem(category = it, onClick)
            }

        }
    }


}


@Composable
fun showLoader() {
    Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            CircularProgressIndicator(

                modifier = Modifier.size(50.dp),
                color = Color.Blue,
                strokeWidth = 4.dp,
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Loading..", style = MaterialTheme.typography.titleLarge)
        }

    }
}


@SuppressLint("ResourceAsColor")
@Composable
fun CategoryItem(category: String = "Shubham", onClick: (category: String) -> Unit) {
    Box(modifier = Modifier

        .size(200.dp, 120.dp)

        .padding(16.dp)
        .clickable {
onClick(category)
        }
        .clip(RoundedCornerShape(4.dp))/*.paint(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop
            )*/.background(Color(0xFF83CAFA))
        .border(
            1.dp, Color(0xFF9D9D9D)
        )) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = category,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = (MaterialTheme.typography.bodyMedium),
                fontStyle = FontStyle.Normal,
                modifier = Modifier
                    .padding(8.dp, 8.dp, 0.dp, 0.dp)
                    .weight(0.3f)
            )
            Text(
                text = "Quotes Fill with Idea",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = (MaterialTheme.typography.bodyMedium),
                fontStyle = FontStyle.Normal,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 0.dp, 0.dp)
                    .weight(0.3f)
            )
            Image(
                contentDescription = "ImageResource",
                painter = painterResource(R.drawable.ic_launcher_foreground),

                modifier = Modifier
                    .weight(0.3f)
                    .align(Alignment.End)
                    .size(40.dp, 40.dp)

                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .border(BorderStroke(1.dp, Color.Black)),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Color.Black)

            )


        }
    }
}

