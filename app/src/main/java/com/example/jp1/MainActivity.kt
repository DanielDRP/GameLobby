package com.example.jp1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jp1.ui.theme.JP1Theme
import dagger.hilt.android.AndroidEntryPoint



object Destinations {
    const val LIST_SCREEN = "LIST_SCREEN"
    const val DETAILS_SCREEN = "DETAILS_SCREEN"
}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JP1Theme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.LIST_SCREEN
                    ){
                        composable(Destinations.LIST_SCREEN){
                            ListScreen(navController = navController)
                        }
                        composable("${Destinations.DETAILS_SCREEN}/{gameId}"){
                            it.arguments?.getString("gameId")?.let{
                                id -> DetailScreen(id,navController)
                            }
                        }
                    }

                }
            }
        }
    }
}

