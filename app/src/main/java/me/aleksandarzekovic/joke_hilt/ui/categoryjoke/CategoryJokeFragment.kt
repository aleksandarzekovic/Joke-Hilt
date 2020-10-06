package me.aleksandarzekovic.joke_hilt.ui.categoryjoke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import me.aleksandarzekovic.joke_hilt.R
import me.aleksandarzekovic.joke_hilt.databinding.CategoryJokeFragmentBinding
import me.aleksandarzekovic.joke_hilt.utils.NetManager
import javax.inject.Inject

class CategoryJokeFragment : DaggerFragment() {

    companion object {
        fun newInstance() = CategoryJokeFragment()
    }

    @Inject
    lateinit var netManager: NetManager
    lateinit var viewModel: CategoryJokeViewModel

    private lateinit var binding: CategoryJokeFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryJokeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.category_joke_fragment,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.clickData.observe(this.viewLifecycleOwner, Observer {
            if(it != null){
                endCategoryJoke(it.name)
            }
        })
        binding.categoryJokeProgressBar.visibility = View.INVISIBLE
    }

    private fun endCategoryJoke(name: String){
        findNavController().navigate(CategoryJokeFragmentDirections.actionCategoryJokeFragmentToJokeFragment(name))
        viewModel.resetData()
    }


}