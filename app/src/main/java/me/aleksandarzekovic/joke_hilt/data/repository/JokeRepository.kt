package me.aleksandarzekovic.joke_hilt.data.repository

import me.aleksandarzekovic.joke_hilt.data.model.Joke
import me.aleksandarzekovic.joke_hilt.data.services.JokeApi
import me.aleksandarzekovic.joke_hilt.utils.NetManager
import me.aleksandarzekovic.joke_hilt.utils.recyclerview.Resource
import javax.inject.Inject

class JokeRepository @Inject constructor(
    private var jokeApi: JokeApi,
    private var netManager: NetManager
) {
    suspend fun getJoke(categoryName: String): Resource<Joke> {
        netManager.isConnectedToInternet?.let {
            if (it) {
                return Resource.Success(jokeApi.getJokeAsync(categoryName))
            }
            return Resource.Failure(Throwable("Not connected to internet."))
        }
        return Resource.Failure(Throwable("Error."))
    }
}