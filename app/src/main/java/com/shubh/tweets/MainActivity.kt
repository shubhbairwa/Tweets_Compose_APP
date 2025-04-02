package com.shubh.tweets

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shubh.tweets.api.TweetsyApi
import com.shubh.tweets.screens.CategoryScreens
import com.shubh.tweets.screens.DetailScreen
import com.shubh.tweets.screens.showLoader
import com.shubh.tweets.ui.theme.TweetsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TweetsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Tweets")
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Black,
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) { // it will give the exact space from top bar
                    Box(modifier = Modifier.padding(it)) {
                       App()

                    }

                }


            }
        }
    }
}

@Composable
fun App() {
    //setup navigation
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        composable("category") {
            CategoryScreens {
                navController.navigate("detail/${it}") //used to navigate
            }
        }
        composable(route = "detail/{category}",
            arguments = listOf(  //if we need to pass data between compose thatn use this
                navArgument("category") {
                    type = NavType.StringType
                }
            )) {
            DetailScreen()

        }

    }
}

