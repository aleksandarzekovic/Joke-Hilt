package me.aleksandarzekovic.joke_hilt.ui.joke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.aleksandarzekovic.joke_hilt.data.model.Joke
import me.aleksandarzekovic.joke_hilt.data.repository.JokeRepository
import me.aleksandarzekovic.joke_hilt.utils.recyclerview.Resource
import java.lang.Exception
import javax.inject.Inject

class JokeViewModel @Inject constructor(private var jokeRepository: JokeRepository) : ViewModel() {

    var joke = MutableLiveData<Resource<Joke>>()

    fun getJokes(categoryName: String) {
        joke.value = Resource.Loading()
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    joke.value = jokeRepository.getJoke(categoryName)
                } catch (e: Exception) {
                    joke.value = Resource.Failure(Throwable(e.message))
                }
            }
        }
    }
}