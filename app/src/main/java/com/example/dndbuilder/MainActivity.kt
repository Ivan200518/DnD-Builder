package com.example.dndbuilder

import UserPreferences
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.dndbuilder.fragments.FragmentActivity
import com.example.dndbuilder.fragments.FragmentMain
import com.example.dndbuilder.presentation.onboard.OnBoardingScreen
import kotlinx.coroutines.launch

class MainActivity : androidx.fragment.app.FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            val context = LocalContext.current
            val userPreferences = remember { UserPreferences(context) }

            var isSignedIn by remember { mutableStateOf<Boolean?>(null) }

            LaunchedEffect(Unit) {
                userPreferences.isSignedIn.collect { signedIn ->
                    isSignedIn = signedIn
                }
            }

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
