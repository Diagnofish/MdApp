package com.example.diagnofish.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.diagnofish.R

data class ArticleItem(
    @DrawableRes val image: Int,
    @StringRes val title: Int
)

val dummyArticleItems = (1..6).map {
    ArticleItem(R.drawable.blank_image, R.string.dummy_short_text)
}