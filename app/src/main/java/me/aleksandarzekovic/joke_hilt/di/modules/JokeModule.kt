package me.aleksandarzekovic.joke_hilt.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.aleksandarzekovic.joke_hilt.ui.joke.JokeFragment
import me.aleksandarzekovic.joke_hilt.ui.joke.JokeViewModel
import me.aleksandarzekovic.joke_hilt.utils.daggerawareviewmodel.ViewModelKey

@Module
abstract class JokeModule {

    @ContributesAndroidInjector
    abstract fun contributeJokeFragment(): JokeFragment

    @Binds
    @IntoMap
    @ViewModelKey(JokeViewModel::class)
    abstract fun bindJokeViewModel(viewModel: JokeViewModel): ViewModel


}