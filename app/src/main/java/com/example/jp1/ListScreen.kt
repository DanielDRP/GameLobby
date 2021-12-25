package com.example.jp1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.jp1.model.Games
import com.example.jp1.ui.theme.JP1Theme

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListScreenViewModel = hiltViewModel()

    ){
    val gamesList by viewModel.getOnlyGames().observeAsState(initial = emptyList())
    val contentList by viewModel.getFreeLoot().observeAsState(initial = emptyList())
    ListScreen(navController, gamesList, contentList)
    }

@Composable
fun ListScreen(
    navController: NavController,
    games: List<Games>,
    loot: List<Games>
){
    val configuration = LocalConfiguration.current
    Scaffold (){
        val context = LocalContext.current
        Column ()
        {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Free Games", Modifier
                            .padding(12.dp), style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                        LazyRow {
                            items(games) { game ->

                                val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(game.open_giveaway_url)) }
                                Card(modifier = Modifier
                                    .padding(12.dp)
                                    .size(
                                        height = 205.dp,
                                        width = (configuration.screenWidthDp - 24).dp
                                    ).clickable {
                                        context.startActivity(intent)
                                    }, elevation = 10.dp) {
                                    Column {
                                        Image(
                                            modifier = Modifier
                                                .fillMaxWidth().aspectRatio(16f/7.2f),
                                            contentScale = ContentScale.Crop,
                                            painter = rememberImagePainter(
                                                data = game.image,
                                                builder = {
                                                    placeholder(R.drawable.ic_launcher_foreground)
                                                    error(R.drawable.ic_launcher_background)
                                                }
                                            ),
                                            contentDescription = "GameImage"

                                        )
                                        Text(text = game.title, fontWeight = FontWeight.Bold, modifier = Modifier
                                            .padding(8.dp), maxLines = 1)
                                    }

                                }
                            }
                        }
                    }

                }

                item {
                    Text(text = "Free Loot", Modifier
                        .padding(12.dp), style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                }
                items(loot) { gameloot ->
                    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(gameloot.open_giveaway_url)) }
                    Row {
                        Card( modifier = Modifier
                            .padding(12.dp)
                            .size(height = 205.dp, width = (configuration.screenWidthDp - 24).dp)
                            .clickable {
                                context.startActivity(intent)
                            }, elevation = 10.dp) {
                            Column{
                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth().aspectRatio(16f/7.2f),
                                    contentScale = ContentScale.Crop,
                                    painter = rememberImagePainter(
                                        data = gameloot.image,
                                        builder = {
                                            placeholder(R.drawable.ic_launcher_foreground)
                                            error(R.drawable.ic_launcher_background)
                                        }
                                    ),
                                    contentDescription = "GameImage"
                                )
                                var title = "No title"
                                if (gameloot.title.isNotEmpty()){
                                    title = gameloot.title
                                }
                                Text(text = title, fontWeight = FontWeight.Bold, modifier = Modifier
                                    .padding(8.dp), maxLines = 1)

                            }

                        }
                    }
                }
            }

        }
}


@Composable
fun ListPreview(){
    JP1Theme(){
        ListScreen(navController = rememberNavController(), games = arrayListOf(
            Games(
                1333,
            "Santa Rockstar",
            "$6.99",
            "https://www.gamerpower.com/offers/1/61c5e97088a1e.jpg",
            "https://www.gamerpower.com/offers/1b/61c5e97088a1e.jpg",
            "Celebrate Christmas with the power of Rock! Grab Santa Rockstar for free via IndieGala and Slay guitar solos as a heavy metal loving Santa.",
            "1. Login into your free IndieGala account.\r\n2. Scroll down and click the \"Add to Your Library\" button to add the game to your library.\r\n3. Go to \"My Library\" to find your game.",
            "https://www.gamerpower.com/open/santa-rockstar",
            "2021-12-24 16:38:24",
            "Full Game",
            "PC, DRM-Free",
            "N/A",
            750,
            "Active",
            "https://www.gamerpower.com/santa-rockstar",
            "https://www.gamerpower.com/open/santa-rockstar"
            ),
            Games(
                1333,
                "Santa Rockstar",
                "$6.99",
                "https://www.gamerpower.com/offers/1/61c5e97088a1e.jpg",
                "https://www.gamerpower.com/offers/1b/61c5e97088a1e.jpg",
                "Celebrate Christmas with the power of Rock! Grab Santa Rockstar for free via IndieGala and Slay guitar solos as a heavy metal loving Santa.",
                "1. Login into your free IndieGala account.\r\n2. Scroll down and click the \"Add to Your Library\" button to add the game to your library.\r\n3. Go to \"My Library\" to find your game.",
                "https://www.gamerpower.com/open/santa-rockstar",
                "2021-12-24 16:38:24",
                "Full Game",
                "PC, DRM-Free",
                "N/A",
                750,
                "Active",
                "https://www.gamerpower.com/santa-rockstar",
                "https://www.gamerpower.com/open/santa-rockstar"
            ),
            Games(
                1333,
                "Santa Rockstar",
                "$6.99",
                "https://www.gamerpower.com/offers/1/61c5e97088a1e.jpg",
                "https://www.gamerpower.com/offers/1b/61c5e97088a1e.jpg",
                "Celebrate Christmas with the power of Rock! Grab Santa Rockstar for free via IndieGala and Slay guitar solos as a heavy metal loving Santa.",
                "1. Login into your free IndieGala account.\r\n2. Scroll down and click the \"Add to Your Library\" button to add the game to your library.\r\n3. Go to \"My Library\" to find your game.",
                "https://www.gamerpower.com/open/santa-rockstar",
                "2021-12-24 16:38:24",
                "Full Game",
                "PC, DRM-Free",
                "N/A",
                750,
                "Active",
                "https://www.gamerpower.com/santa-rockstar",
                "https://www.gamerpower.com/open/santa-rockstar"
            )
        ),loot = arrayListOf(
            Games(
                1333,
                "Santa Rockstar",
                "$6.99",
                "https://www.gamerpower.com/offers/1/61c5e97088a1e.jpg",
                "https://www.gamerpower.com/offers/1b/61c5e97088a1e.jpg",
                "Celebrate Christmas with the power of Rock! Grab Santa Rockstar for free via IndieGala and Slay guitar solos as a heavy metal loving Santa.",
                "1. Login into your free IndieGala account.\r\n2. Scroll down and click the \"Add to Your Library\" button to add the game to your library.\r\n3. Go to \"My Library\" to find your game.",
                "https://www.gamerpower.com/open/santa-rockstar",
                "2021-12-24 16:38:24",
                "Full Game",
                "PC, DRM-Free",
                "N/A",
                750,
                "Active",
                "https://www.gamerpower.com/santa-rockstar",
                "https://www.gamerpower.com/open/santa-rockstar"
            ),
            Games(
                1333,
                "Santa Rockstar",
                "$6.99",
                "https://www.gamerpower.com/offers/1/61c5e97088a1e.jpg",
                "https://www.gamerpower.com/offers/1b/61c5e97088a1e.jpg",
                "Celebrate Christmas with the power of Rock! Grab Santa Rockstar for free via IndieGala and Slay guitar solos as a heavy metal loving Santa.",
                "1. Login into your free IndieGala account.\r\n2. Scroll down and click the \"Add to Your Library\" button to add the game to your library.\r\n3. Go to \"My Library\" to find your game.",
                "https://www.gamerpower.com/open/santa-rockstar",
                "2021-12-24 16:38:24",
                "Full Game",
                "PC, DRM-Free",
                "N/A",
                750,
                "Active",
                "https://www.gamerpower.com/santa-rockstar",
                "https://www.gamerpower.com/open/santa-rockstar"
            ),
            Games(
                1333,
                "Santa Rockstar",
                "$6.99",
                "https://www.gamerpower.com/offers/1/61c5e97088a1e.jpg",
                "https://www.gamerpower.com/offers/1b/61c5e97088a1e.jpg",
                "Celebrate Christmas with the power of Rock! Grab Santa Rockstar for free via IndieGala and Slay guitar solos as a heavy metal loving Santa.",
                "1. Login into your free IndieGala account.\r\n2. Scroll down and click the \"Add to Your Library\" button to add the game to your library.\r\n3. Go to \"My Library\" to find your game.",
                "https://www.gamerpower.com/open/santa-rockstar",
                "2021-12-24 16:38:24",
                "Full Game",
                "PC, DRM-Free",
                "N/A",
                750,
                "Active",
                "https://www.gamerpower.com/santa-rockstar",
                "https://www.gamerpower.com/open/santa-rockstar"
            )
        ))
    }}}



