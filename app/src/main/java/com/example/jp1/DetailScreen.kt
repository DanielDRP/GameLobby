package com.example.jp1

import android.content.Intent
import android.net.Uri
import android.telecom.Call
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.ImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.example.jp1.model.Games
import com.example.jp1.ui.theme.JP1Theme


@Composable
fun DetailScreen(
    gameId: String,
    navController: NavController,
    viewModel: DetailScreenViewModel = hiltViewModel()) {
    val game by viewModel.getGamesById(gameId).observeAsState(initial = null)
    DetailScreen(gameId = gameId, navController = navController, game)
}
@Composable
fun DetailScreen(
    gameId: String,
    navController: NavController,
    game: Games?
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { game?.title?.let { Text(text = it) } },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Column {
                Image(
                    modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f),
                    painter = rememberImagePainter(
                        data = game!!.image,
                        builder = {
                            placeholder(R.drawable.ic_launcher_foreground)
                            error(R.drawable.ic_launcher_background)
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Column(Modifier.padding(8.dp)) {
                    val context = LocalContext.current

                    Text(game!!.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(game.description ?: "")
                    Box(Modifier.size(8.dp))
                    Button(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(game.open_giveaway_url))
                            context.startActivity(intent)
                        }) {
                        Text("Ver mas...")
                    }
                }
            }
        }
    } ?: run {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }

}
}

@Preview(showBackground = true)
@Composable
fun DetailsPreview(){
    JP1Theme{
        DetailScreen(
            gameId = "1333",
            navController = rememberNavController(),
            game = fgame
        )
    }
}
val fgame = Games(
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