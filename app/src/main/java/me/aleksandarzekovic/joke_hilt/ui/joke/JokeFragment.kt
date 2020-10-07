package me.aleksandarzekovic.joke_hilt.ui.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import me.aleksandarzekovic.joke_hilt.R
import me.aleksandarzekovic.joke_hilt.databinding.JokeFragmentBinding
import me.aleksandarzekovic.joke_hilt.utils.recyclerview.Resource
import javax.inject.Inject

@AndroidEntryPoint
class JokeFragment : Fragment() {

    companion object {
        fun newInstance() = JokeFragment()
    }

    lateinit var viewModel: JokeViewModel

    lateinit var binding: JokeFragmentBinding

    lateinit var categoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)
            .get(JokeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.joke_fragment,
            container,
            false
        )
        categoryName = arguments?.get("category").toString()
        binding.category = categoryName
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getJokes(categoryName)
        viewModel.joke.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.jokeProgressBar.visibility = View.INVISIBLE
                    binding.joke = it.data
                }

                is Resource.Failure -> {
                    binding.jokeProgressBar.visibility = View.INVISIBLE
                    Toast.makeText(this.context, "${it.throwable.message}", Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.Loading -> {
                    binding.jokeProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }

}