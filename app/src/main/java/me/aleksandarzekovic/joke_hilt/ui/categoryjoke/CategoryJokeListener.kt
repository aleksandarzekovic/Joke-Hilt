package me.aleksandarzekovic.joke_hilt.ui.categoryjoke

interface CategoryJokeListener<T> {
    fun clickCategoryJoke(model: T)
}