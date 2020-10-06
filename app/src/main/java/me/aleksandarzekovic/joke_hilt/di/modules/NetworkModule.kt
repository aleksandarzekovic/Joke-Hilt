package me.aleksandarzekovic.joke_hilt.di.modules

import dagger.Module
import dagger.Provides
import me.aleksandarzekovic.joke_hilt.data.services.JokeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    private val BASE_URL = "https://sv443.net"

    @Provides
    fun provideRetrofit(): JokeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeApi::class.java)
    }
}