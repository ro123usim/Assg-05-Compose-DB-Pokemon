/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


// Started with Android Developer code sample
// Code available at https://developer.android.com/codelabs/jetpack-compose-theming#0
// ...then swapped in a DB to populate the compose list

package com.codelab.basics

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codelab.basics.ui.theme.BasicsCodelabTheme
import com.codelab.basics.ui.theme.Blue

/**
 * Sample DB Compose app with Master-Details pages
 * ShowPageMaster ... shows the list of DB elements
 * ShowPageDetails ... shows the detail contents of one element
 *
 * Added Adaptive behavior...
 *  - show master and details on different screens
 *  - if landscape, show master and details side-by-side
 *
 * Use the logcat to follow the logic.
 *
 * It's waiting for real data....
 */
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val DBtest = DBClass(this)

        setContent {
            BasicsCodelabTheme {
                MyApp(
                    modifier = Modifier.fillMaxSize(),
                    DBtest = DBtest
                )
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    DBtest: DBClass
) {
    var names by remember { mutableStateOf(DBtest.findAll()) }
    val windowInfo = rememberWindowInfo()
    var index by remember { mutableIntStateOf(-1) }

    Surface(
        modifier = modifier.background(Color(0xFFE3F2FD)),
        color = Color(0xFFE3F2FD)
    ) {

        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {

            if (index < 0 || index >= names.size) {

                ShowPageMaster(
                    names = names,
                    updateIndex = { index = it },
                    DBtest = DBtest,
                    refresh = { names = DBtest.findAll() }
                )

            } else {

                ShowPageDetails(
                    name = names[index],
                    index = index,
                    updateIndex = { index = it }
                )
            }

        } else {

            if (index < 0) index = 0

            Row(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Blue)
                ) {
                    ShowPageMaster(
                        names = names,
                        updateIndex = { index = it },
                        DBtest = DBtest,
                        refresh = { names = DBtest.findAll() }
                    )
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color.Red)
                ) {
                    ShowPageDetails(
                        name = names[index],
                        index = index,
                        updateIndex = { index = it }
                    )
                }
            }
        }
    }
}


@Composable
private fun ShowPageMaster(
    modifier: Modifier = Modifier,
    names: List<DataModel>,
    updateIndex: (index: Int) -> Unit,
    DBtest: DBClass,
    refresh: () -> Unit
) {

    val favoriteId = DBtest.getMostAccessed()
    val favorite = names.find { it.id == favoriteId.toInt() }

    Column {

        // ⭐ Favorite Pokémon Display
        favorite?.let {
            Text(
                text = "⭐ Favorite Pokémon: ${it.name}",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp),
                color = Color.Magenta
            )
        }

        LazyColumn(
            modifier = modifier.padding(vertical = 4.dp)
        ) {
            itemsIndexed(items = names) { pos, name ->
                ShowEachListItem(
                    name = name,
                    pos = pos,
                    updateIndex = updateIndex,
                    DBtest = DBtest,
                    refresh = refresh
                )
            }
        }
    }
}

@Composable
private fun ShowEachListItem(
    name: DataModel,
    pos: Int,
    updateIndex: (index: Int) -> Unit,
    DBtest: DBClass,
    refresh: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor =
                if (name.powerLevel > 80) Color(0xFFFFCDD2)
                else MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)
    ) {
        CardContent(name, pos, updateIndex, DBtest, refresh)
    }
}

@Composable
private fun CardContent(
    name: DataModel,
    pos: Int,
    updateIndex: (index: Int) -> Unit,
    DBtest: DBClass,
    refresh: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                ),
                onClick = {
                    updateIndex(pos)
                    DBtest.incAccessCount(name.id.toLong())
                    refresh()
                }
            ) {
                Text(text = "View Details")
            }

            Text(
                text = name.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Power Level: ${name.powerLevel}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Viewed: ${name.accessCount} times",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )

            if (expanded) {
                Text(
                    text = name.description,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
                contentDescription = null
            )
        }
    }
}


@Composable
private fun ShowPageDetails(
    name: DataModel,
    updateIndex: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    index: Int
) {
    val windowInfo = rememberWindowInfo()  // sorta global, not good
    Column(
        modifier = modifier.fillMaxWidth(0.5f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(name.toString())
        Log.d("CodeLab_DB", "ShowData: $name.toString()")

        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            Button(onClick = { updateIndex(-1) })
            { Text(text = "Master") }
        }
        // need check for end of array
        Button(onClick = { updateIndex(index + 1) })
        { Text(text = "Next") }
        if (index > 0) {
            Button(onClick = { updateIndex(index - 1) })
            { Text(text = "Prev") }
        }
    }
}