package com.example.dndbuilder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dndbuilder.presentation.onboard.OnBoardingScreen
import com.example.dndbuilder.ui.theme.DndBuilderTheme

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
