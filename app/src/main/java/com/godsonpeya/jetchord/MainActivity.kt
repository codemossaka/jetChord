package com.godsonpeya.jetchord

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godsonpeya.jetchord.model.local.VersesLine
import com.godsonpeya.jetchord.model.network.GuitarText
import com.godsonpeya.jetchord.ui.theme.JetChordTheme
import com.godsonpeya.jetchord.utils.getJsonFromAssets
import com.squareup.moshi.Moshi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetChordTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.size(200.dp),
                    color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greetidng(name: String) {
    val menus =
        listOf("Menu 1", "Menu 2", "Menu 3", "Menu 4", "Menu 5", "Menu 6", "Menu 7", "Menu 8", "Menu 2", "Menu 3", "Menu 4", "Menu 5", "Menu 6", "Menu 7", "Menu 8", "Menu 2", "Menu 3", "Menu 4", "Menu 5", "Menu 6", "Menu 7", "Menu 8", "Menu 2", "Menu 3", "Menu 4", "Menu 5", "Menu 6", "Menu 7", "Menu 8", "Menu 2", "Menu 3", "Menu 4", "Menu 5", "Menu 6", "Menu 7", "Menu 8")

    LazyHorizontalGrid(rows = GridCells.Fixed(4)){
           items(menus){menu->
               CardDeProduit(menu)

           }
       }
}

@Composable
fun CardDeProduit(menu:String){
    Box(modifier = Modifier
        .padding(5.dp)
        .size(150.dp)
        .background(Color.Green), contentAlignment = Alignment.Center){
        Text(text = menu,
            modifier = Modifier
                .padding(15.dp))
    }
}

@Composable
fun Greeting(name: String) {
    val versesList = arrayListOf<VersesLine>()

    var guitarVerses: GuitarText? = null
    var currentVerseTypeIndex = 0
    val applicationContext = LocalContext.current
    val guitarVersesJson = getJsonFromAssets(applicationContext, "GuitarVerses.json")
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(GuitarText::class.java)
    val versesLines = mutableListOf<VersesLine>()
    if (guitarVersesJson != null) {
        guitarVerses = jsonAdapter.fromJson(guitarVersesJson)
        guitarVerses?.verses?.forEach { verse ->
            verse.text.split("<br>").forEach {
                if (it.isNotEmpty()) {
                    Log.d("LOG_TAG", "MainActivity: onCreate: splittedByLines: $it")
                    versesLines.add(getVersesLines(it))
                }
            }
        }

    }

    LazyColumn(content = {
        item {
            LazyRow(content = {
                items(versesLines) { versesLine ->
                    if (versesLine.accord.isNotEmpty()) {
                        Text(text = versesLine.accord, fontFamily = FontFamily.Monospace)
                    }
                    Text(text = versesLine.text, fontFamily = FontFamily.Monospace)

                }
            })
        }
        item {
            LazyRow(content = {
                items(versesLines) { versesLine ->
                    if (versesLine.accord.isNotEmpty()) {
                        Text(text = versesLine.accord, fontFamily = FontFamily.Monospace)
                    }
                    Text(text = versesLine.text, fontFamily = FontFamily.Monospace)

                }
            })
        }
        items(versesLines) { versesLine ->
            if (versesLine.accord.isNotEmpty()) {
                Text(text = versesLine.accord, fontFamily = FontFamily.Monospace)
            }
            Text(text = versesLine.text, fontFamily = FontFamily.Monospace)

        }
    })
}

fun getVersesLines(initialText: String): VersesLine {
//    val regex = """^\d\.""".toRegex()
//    val inputText = regex.replace(initialText, "").trim()

    val separatedAccords = initialText.split("[", "]")

    val finalText = StringBuilder()
    val accords = StringBuilder()



    separatedAccords.forEachIndexed { index, s ->
        if (index % 2 == 0) {
            if (!s.startsWith(" ")) {
                finalText.append(s)

//                for (i in 2..s.length) {
//                    accords.append(" ")
//                }
            }

        } else {
            accords.append(s)
            if (separatedAccords[index + 1].isNotEmpty()) {
                val limit = separatedAccords[index + 1].length - s.length - 1

                for (n in 0 until separatedAccords[index + 1].length - s.length) {
                    accords.append(" ")
                }
            }

        }
    }

    return VersesLine(accords.toString(), finalText.toString())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetChordTheme {
        Greeting("Android")
    }
}