package com.godsonpeya.jetchord.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.godsonpeya.jetchord.R

@Composable
fun CardView (){
    Card(elevation = 4.dp, modifier = Modifier.padding(20.dp), shape = RoundedCornerShape(15.dp) ) {
        Box(modifier = Modifier.size(300.dp)) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier.size(300.dp))

            Box(modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = listOf(Color.Transparent,
                    Color.Black), startY = 300f)))

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), contentAlignment = Alignment.BottomStart) {
                Text(text = "Djbf rjfbnf fkfbcfc cjcfhfcfc", color = Color.White)
            }
        }
    }
}