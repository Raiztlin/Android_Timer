package com.example.testingground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testingground.ui.theme.TestingGroundTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {
                TimerComposable()
            }
        }
    }
}

@Composable
fun TimerComposable(){
    val time = 10
    var counter by remember { mutableStateOf(time) }
    var isRunning by remember { mutableStateOf(false) }
    var reset by remember { mutableStateOf(false) }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
            ) {

        LaunchedEffect(key1 = isRunning, key2 = reset) {
            if (reset) {
                counter = time
                reset = !reset
            } else if (isRunning) {
                for (i in 1..counter) {
                    delay(1000L)
                    counter = counter - 1
                }
            } else
                counter = counter
        }

        Text(
            text = "${counter}",
            color = Color.White,
            fontSize = 200.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = {
                    isRunning = !isRunning
                },
                modifier = Modifier
                    .size(height = 50.dp, width = 100.dp)
            ) {
                Text(
                    if (isRunning) "Stop" else "Start",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (isRunning || counter < time) {
                Button(
                    onClick = {
                        reset = !reset
                        if (isRunning){
                            isRunning = !isRunning
                        }
                    },
                    modifier = Modifier
                        .size(height = 50.dp, width = 140.dp)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = "Reset",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else
                true
        }
    }
}
