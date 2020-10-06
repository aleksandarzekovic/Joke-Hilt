package me.aleksandarzekovic.joke_hilt.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.aleksandarzekovic.joke_hilt.di.modules.AppModule
import me.aleksandarzekovic.joke_hilt.di.modules.CategoryJokeModule
import me.aleksandarzekovic.joke_hilt.di.modules.JokeModule
import me.aleksandarzekovic.joke_hilt.di.modules.NetworkModule
import me.aleksandarzekovic.joke_hilt.utils.daggerawareviewmodel.ViewModelBuilder
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class, AppModule::class, JokeModule::class, CategoryJokeModule::class, ViewModelBuilder::class])
interface AppComponent : AndroidInjector<JokeHiltApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<JokeHiltApplication>
}