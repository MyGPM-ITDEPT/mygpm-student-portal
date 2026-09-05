package com.example.studentapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.studentapp2.ui.login.LoginScreen
import com.example.studentapp2.ui.theme.MyGPMTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyGPMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LoginScreen(
                        onLoginSuccess = {
                            // TODO: Navigate to dashboard once it is created.
                        }
                    )
                }
            }
        }
    }
}