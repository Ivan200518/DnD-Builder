package com.example.lingvomatenew.presentation.auth.onboard

import androidx.annotation.DrawableRes
import com.example.dndbuilder.R

sealed class OnBoardingModel(
    @DrawableRes val image: Int,
    val title : String,
    val description : String
){
    data object FirstPage : OnBoardingModel(
        image = R.drawable.onboarding1,
        title = "Create a new character",
        description = "Build a unique character from \n" +
                "race to class — the adventure \n" +
                "starts with you.\n" +
                " "
    )

    data object SecondPage : OnBoardingModel(
        image = R.drawable.onboarding2,
        title = "Embrace the Unexpected",
        description = "\n" +
                "Share your characters, join \n" +
                "campaigns, and let randomness\n" +
                " write your legend. A single\n" +
                " roll can change everything\n" +
                " — are you ready?"
    )

    data object ThirdPage : OnBoardingModel(
        image = R.drawable.onboarding3,
        title = "Enter a Living World",
        description = "\n" +
                "From elven forests to shadowy\n" +
                " dungeons, adventure awaits. All rules, \n" +
                "spells, and gear are right at\n" +
                " your fingertips — no books needed."
    )
}