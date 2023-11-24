package com.mo.kmp_hello_world.android.MainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mo.kmp_hello_world.android.MyApplicationTheme
import com.mo.kmp_hello_world.data.models.RocketLaunch

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val greetings = mainViewModel.rocketsList.collectAsStateWithLifecycle()
                    val isLoading = mainViewModel.isLoading.collectAsStateWithLifecycle()
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        if (isLoading.value) {
                            CircularProgressIndicator(modifier = Modifier.size(40.dp) , color = Color.Gray)
                        }else {
                            Column {
                                AppHeader(text = "SpaceX Rockets")
                                RocketsList(rockets = greetings.value)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AppHeader(text: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
        .padding(top = 20.dp , bottom = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text , fontSize = 18.sp , color = Color.White , fontWeight = FontWeight(700))
    }
}

@Composable
fun RocketsList(rockets: List<RocketLaunch>) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(rockets) { rocket ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(CornerSize(14.dp)))
                    .fillMaxWidth()
                    .background(color = Color.Gray)
                    .clickable {

                    }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row {
                        Text("Mission Name : ", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(rocket.missionName, color = Color.Black, fontSize = 12.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Text("Mission Date  : ", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(rocket.launchDate, color = Color.Black, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}
