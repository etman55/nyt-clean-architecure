package com.example.nytcleanarcitecture.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nytcleanarcitecture.R
import com.example.nytcleanarcitecture.base.Resource
import com.example.nytcleanarcitecture.base.Status
import com.example.nytcleanarcitecture.base.extension.viewBinding
import com.example.nytcleanarcitecture.databinding.FragmentDetailsBinding
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.ui.main.MainNavigation
import com.example.nytcleanarcitecture.ui.main.MainViewModel
import com.example.nytcleanarcitecture.utils.ImageLoader
import com.example.nytcleanarcitecture.utils.onBackPress
import com.example.nytcleanarcitecture.utils.snackErrorWithAction
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentDetailsBinding by viewBinding(FragmentDetailsBinding::bind)
    private val args: DetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            getArticleById(id = args.id)
            observeNavigation(viewLifecycleOwner, ::handleNavigation)
            article.observe(viewLifecycleOwner, ::handlePopulateArticleDetails)
        }
        this.onBackPress {
            viewModel.navigateBack()
        }
    }

    private fun handlePopulateArticleDetails(resource: Resource<Article>) {
        when (resource.status) {
            Status.LOADING -> {
            }
            Status.SUCCESS -> {
                resource.data?.let { article ->
                    binding.articleTitleTxt.text = article.title
                    binding.articleDescTxt.text = article.abstract
                    val media = article.media.first { it.type == "image" }
                    imageLoader.loadImage(binding.articleImg, media.mediaMetadata[2].url)
                }
            }
            Status.ERROR -> {
                resource.message?.let {
                    binding.articleTitleTxt.snackErrorWithAction(it, action = {
                        viewModel.getArticleById(id = args.id)
                    })
                }
            }
        }
    }

    private fun handleNavigation(navigation: MainNavigation) {
        if (navigation is MainNavigation.Back)
            findNavController().navigateUp()
    }
}
