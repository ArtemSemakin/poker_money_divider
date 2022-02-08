package ru.techcrat.poker_money_divider.screens.homescreen

import android.content.Context
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import ru.techcrat.poker_money_divider.R
import ru.techcrat.poker_money_divider.models.Combination
import ru.techcrat.poker_money_divider.nav.NavigationItem

@Composable
fun HomeScreen(list: List<Combination>, context: Context, navController: NavHostController) {
    InitLazyColumn(list = list, navController)
}

@Composable
fun InitLazyColumn(list: List<Combination>, navController: NavHostController) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(list) { item ->
            var expanded by remember { mutableStateOf(false) }
            Card(
                border = BorderStroke(2.dp, color = Color.Black),
                shape = MaterialTheme.shapes.medium,
                elevation = 30.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    //.height(140.dp)
                    .padding(10.dp)
                    .animateContentSize()
            ) {
                Column() {
                    Row() {
                        CombinationDetailsScreen(combination = item)
                        Image(
                            modifier = Modifier
                                .height(48.dp)
                                .padding(start = 10.dp, top = 10.dp)
                                .clickable {
                                    expanded = !expanded
                                },
                            painter = painterResource(id = R.drawable.info_icon),
                            contentDescription = null,

                            )
                    }
                    Box(contentAlignment = Alignment.TopCenter
                    ) {
                        (if (expanded)stringResource(id = item.descriptionResId) else null)?.let {
                            Text(
                                text = it, fontSize = 16.sp,
                                modifier = Modifier.padding(start = 28.dp, bottom = 4.dp),
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }


            }

        }
    }

}

private fun navigateToNewScreen(
    navController: NavHostController,
    route: String,
    combination: Combination
) {
    Log.d("combination_1", Gson().toJson(combination))
    navController.navigate("$route/${Gson().toJson(combination)}") {
        navController.graph.route?.let { rout ->
            popUpTo(rout) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

