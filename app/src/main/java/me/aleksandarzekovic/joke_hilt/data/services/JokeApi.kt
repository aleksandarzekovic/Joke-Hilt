package me.aleksandarzekovic.joke_hilt.data.services

import me.aleksandarzekovic.joke_hilt.data.model.Joke
import retrofit2.http.GET
import retrofit2.http.Path

interface JokeApi {
    @GET("/jokeapi/v2/joke/{category}")
    suspend fun getJokeAsync(@Path("category") category: String): Joke
}