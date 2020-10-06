package me.aleksandarzekovic.joke_hilt.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import me.aleksandarzekovic.joke_hilt.di.JokeHiltApplication
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun context(appInstance: JokeHiltApplication): Context
}