package ru.techcrat.poker_money_divider.screens.homescreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import ru.techcrat.poker_money_divider.R
import ru.techcrat.poker_money_divider.nav.NavigationItem

@Composable
fun HomeScreen(list: List<String>, context: Context, navController: NavHostController) {
    InitLazyColumn(list = list, context = context, navController)
}

@Composable
fun InitLazyColumn(list: List<String>, context: Context, navController: NavHostController) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(list) { item ->
            Card(elevation = 12.dp, modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
                .height(40.dp)
                .clickable { navigateToNewScreen(navController, NavigationItem.NewScreen.route) }
            ){
                Row(
                    Modifier
                        .height(20.dp)
                        .fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "gfgdfgd",
                        Modifier.padding(8.dp)
                    )
                    Text(text = item, textAlign = TextAlign.Center, fontSize = 24.sp)
                }
            }

        }
    }

}

fun generateData(): List<String> {
    return listOf("Artem")
}

private fun navigateToNewScreen(navController: NavHostController, route: String) {
    navController.navigate(route) {
        navController.graph.route?.let { rout ->
            popUpTo(rout) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
