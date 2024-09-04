package com.example.findmyip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.findmyip.ui.theme.MainAppTheme

class IPActivity : ComponentActivity() {
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IpInfoScreen(viewModel)
            }
        viewModel.fetchIPInfo()
    }
}

@Composable
fun IpInfoScreen(viewModel: MainViewModel) {
    val ipInfo by viewModel.ipInfo.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if(loading){
            CircularProgressIndicator()
        }else{
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ipInfo?.let{
                    Text(text = "IP: ${it.ip}")
                    Text(text = "netWork: ${it.network}")
                    Text(text = "City: ${it.ip}")
                    Text(text = "region: ${it.ip}")
                }

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainAppTheme {
        IpInfoScreen(viewModel = MainViewModel())
    }
}