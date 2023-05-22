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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.delay
import java.security.AllPermission

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerComposable(){
    var acceptInput by remember { mutableStateOf(true) }
    var counter by remember { mutableStateOf("0") }
    var isRunning by remember { mutableStateOf(false) }
    var reset by remember { mutableStateOf(false) }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
            ) {

        LaunchedEffect(key1 = isRunning, key2 = reset) {
            if (reset) {
                //counter = time // oklart hur den här logiken ska se ut
                counter = "0"
                reset = !reset
            } else if (isRunning) {
                for (i in 1..counter.toInt()) {
                    delay(1000L)
                    counter = (counter.toInt() - 1).toString()
                }
            } else
                counter = counter
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

        if (isRunning && !acceptInput) { // Appen krashar om jag har den här if sattsen.
            Text(
                text = "${counter}",
                color = Color.White,
                fontSize = 200.sp,
                fontWeight = FontWeight.Bold
            )
        } else
            TextField(
                value = "${counter}",
                label = {Text("Set timer")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                onValueChange = {
                    counter = it
                }
            )
            //SetTimerCount(onInput = { counter = it.toInt()})
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = {
                    isRunning = !isRunning
                    acceptInput = !acceptInput
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

            //if (isRunning && !acceptInput) {
                //Button(
                    //onClick = {
                        //reset = !reset
                        //if (isRunning){
                            //isRunning = !isRunning
                        //}
                    //},
                    //modifier = Modifier
                        //.size(height = 50.dp, width = 140.dp)
                        //.padding(start = 16.dp)
                //) {
                    //Text(
                        //text = "Reset",
                        //color = Color.White,
                        //fontSize = 20.sp,
                        //fontWeight = FontWeight.Bold
                    //)
                //}
            //}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetTimerCount(onInput: (String) -> Unit){
    TextField(
        value = "0",
        label = {Text("Set timer")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        onValueChange = {
            onInput(it)
        }
    )
}
