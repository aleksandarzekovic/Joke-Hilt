package me.aleksandarzekovic.joke_hilt.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.aleksandarzekovic.joke_hilt.ui.categoryjoke.CategoryJokeFragment

@Module
abstract class CategoryJokeModule {

    @ContributesAndroidInjector
    abstract fun contributeCategoryJokeFragment(): CategoryJokeFragment

}