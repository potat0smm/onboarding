package com.example.onboarding

import androidx.annotation.DrawableRes

data class OnBoardingData(
    val title:String,
    val description: String,
    @DrawableRes val image: Int
)
