package com.example.mygpmadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mygpmadmin.ui.theme.MyGPMAdminTheme
import com.example.mygpmadmin.ui.theme.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyGPMAdminTheme {
                AppNavigation()
            }
        }
    }
}