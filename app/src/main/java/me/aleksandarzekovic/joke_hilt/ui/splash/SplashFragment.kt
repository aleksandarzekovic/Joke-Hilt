package me.aleksandarzekovic.joke_hilt.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.aleksandarzekovic.joke_hilt.R

class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        GlobalScope.launch {
            delay(3000)
            view?.findNavController()?.navigate(R.id.action_splashFragment_to_categoryJokeFragment)
        }
    }

}