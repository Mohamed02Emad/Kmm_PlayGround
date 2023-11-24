package com.mo.kmp_hello_world.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mo.kmp_hello_world.Greeting

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(
                        content = {
                            stickyHeader {
                                MyHeader(text = Greeting().greet() + " One")
                            }
                            items(50) { index ->
                                IndexCard(index = index)
                            }
                            stickyHeader {
                                MyHeader(text = Greeting().greet() + " Two")
                            }
                            items(50) { index ->
                                IndexCard(index = index+50)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Composable
fun IndexCard(index: Int) {
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 12.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.Blue)
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "index $index")

    }
}

@Composable
fun MyHeader(text: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp , horizontal = 12.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.Red)
            .fillMaxWidth(),
//            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)

    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
