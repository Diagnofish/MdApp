package com.example.diagnofish.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.diagnofish.R

data class HistoryItem(
    @DrawableRes val image: Int,
    @StringRes val status: Int,
    @StringRes val date: Int,
    @StringRes val fishName: Int,
)

val dummyHistoryItems = (1..6).map{
    HistoryItem(R.drawable.blank_image, listOf(R.string.status_healthy, R.string.status_infected).random(), R.string.dummy_date, R.string.dummy_fish_name)
}
