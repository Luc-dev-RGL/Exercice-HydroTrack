package com.example.hydrotrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hydrotrack.ui.WaterTrackerScreen
import com.example.hydrotrack.ui.theme.HydroTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HydroTrackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WaterTrackerScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Aperçu HydroTrack")
@Composable
fun MainActivityPreview() {
    HydroTrackTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            WaterTrackerScreen()
        }
    }
}