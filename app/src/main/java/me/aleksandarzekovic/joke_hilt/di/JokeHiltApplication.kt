package me.aleksandarzekovic.joke_hilt.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class JokeHiltApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}