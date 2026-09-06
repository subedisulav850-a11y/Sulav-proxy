package com.sulav.proxy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

data class Traffic(
    val method: String,
    val endpoint: String,
    val status: Int,
    val requestHex: String,
    val responseHex: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SulavProxyApp() }
    }
}

@Composable
fun SulavProxyApp() {
    var url by remember { mutableStateOf("http://127.0.0.1:8080/") }
    var host by remember { mutableStateOf("127.0.0.1") }
    var port by remember { mutableStateOf("8080") }
    var running by remember { mutableStateOf(false) }
    var showHex by remember { mutableStateOf(false) }
    var traffic by remember { mutableStateOf(listOf<Traffic>()) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color(0xFF090B10),
            surface = Color(0xFF11141B),
            primary = Color(0xFF7C5CFF)
        )
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Sulav Proxy") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF0D1016)
                    )
                )
            }
        ) { pad ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(pad)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text("Proxy Configuration", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = url,
                        onValueChange = { url = it },
                        label = { Text("URL") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = host,
                            onValueChange = { host = it },
                            label = { Text("Host") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = port,
                            onValueChange = { port = it },
                            label = { Text("Port") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { running = !running },
                            modifier = Modifier.weight(1f)
                        ) { Text(if (running) "Stop Proxy" else "Start Proxy") }
                        OutlinedButton(
                            onClick = {
                                url = "http://127.0.0.1:8080/"
                                host = "127.0.0.1"
                                port = "8080"
                            },
                            modifier = Modifier.weight(1f)
                        ) { Text("Reset") }
                    }
                }
                item {
                    Card {
                        Column(Modifier.padding(16.dp)) {
                            Text("LocalConfig", style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(6.dp))
                            Text("""{"serverLoginUrl":"http://127.0.0.1:8080/"}""")
                            Spacer(Modifier.height(10.dp))
                            Button(onClick = { /* Android file export can be added with SAF */ }) {
                                Text("Export localconfig")
                            }
                        }
                    }
                }
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterChip(
                            selected = true,
                            onClick = {},
                            label = { Text("All Traffic") }
                        )
                        FilterChip(
                            selected = showHex,
                            onClick = { showHex = !showHex },
                            label = { Text("HEX") }
                        )
                        OutlinedButton(onClick = { traffic = emptyList() }) {
                            Text("Clear All")
                        }
                    }
                }
                items(traffic) { item ->
                    Card {
                        Column(Modifier.padding(14.dp)) {
                            Text("${item.method}  ${item.endpoint}")
                            Text("Status: ${item.status}")
                            if (showHex) {
                                Spacer(Modifier.height(6.dp))
                                Text("Request HEX: ${item.requestHex}")
                                Text("Response HEX: ${item.responseHex}")
                            }
                        }
                    }
                }
                item {
                    Card {
                        Column(Modifier.padding(16.dp)) {
                            Text("Owner", style = MaterialTheme.typography.titleMedium)
                            Text("Telegram: @sulavji")
                            Text("YouTube: @ExeSasukeYT")
                        }
                    }
                }
            }
        }
    }
}
