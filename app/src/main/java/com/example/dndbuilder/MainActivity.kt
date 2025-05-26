package com.example.dndbuilder

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.dndbuilder.fragments.FragmentActivity
import com.example.dndbuilder.presentation.onboard.OnBoardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{

            ShowOnboardingScreen()
        }
    }

}
@Composable
private fun ShowOnboardingScreen() {

    val context = LocalContext.current

    Box(modifier = Modifier.background(color = colorResource(R.color.maroun))){
        OnBoardingScreen {
            val intent = Intent(context, FragmentActivity::class.java)
            context.startActivity(intent)
        }
    }
}
