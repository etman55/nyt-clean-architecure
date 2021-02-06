package com.example.nytcleanarcitecture.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nytcleanarcitecture.R
import com.example.nytcleanarcitecture.base.Resource
import com.example.nytcleanarcitecture.base.Status
import com.example.nytcleanarcitecture.base.extension.viewBinding
import com.example.nytcleanarcitecture.databinding.FragmentMainBinding
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.utils.ImageLoader
import com.example.nytcleanarcitecture.utils.snack
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels { defaultViewModelProviderFactory }

    @Inject
    lateinit var imageLoader: ImageLoader

    private var articlesAdapter: ArticlesAdapter? = null

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observeNavigation(viewLifecycleOwner, ::handleNavigation)
            getArticles()
            articles.observe(viewLifecycleOwner, ::handleArticlesList)
        }
        articlesAdapter = ArticlesAdapter(imageLoader) {
            viewModel.navigateToArticleDetails(it.id)
        }
        binding.articlesList.apply {
            adapter = articlesAdapter
            setHasFixedSize(true)
        }
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getArticles()
        }
    }

    private fun handleArticlesList(resource: Resource<List<Article>>) {
        when (resource.status) {
            Status.LOADING -> {
                binding.refreshLayout.isRefreshing = true
            }
            Status.SUCCESS -> {
                binding.refreshLayout.isRefreshing = false
                resource.data?.let { articlesAdapter?.items = it }
            }
            Status.ERROR -> {
                binding.refreshLayout.isRefreshing = false
                resource.message?.let { binding.refreshLayout.snack(it) }
            }
        }
    }

    private fun handleNavigation(navigation: MainNavigation) {
        when (navigation) {
            is MainNavigation.ArticleDetails -> {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(navigation.articleId))
            }
            MainNavigation.Back -> {
            }
        }
    }
}
