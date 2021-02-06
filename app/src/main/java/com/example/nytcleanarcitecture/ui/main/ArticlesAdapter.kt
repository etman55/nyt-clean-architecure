package com.example.nytcleanarcitecture.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nytcleanarcitecture.databinding.ItemArticleBinding
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.utils.ImageLoader
import com.example.nytcleanarcitecture.utils.adapterProperty
import javax.inject.Inject

class ArticlesAdapter @Inject constructor(
    private val imageLoader: ImageLoader,
    private val onCLick: (item: Article) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    var items: List<Article> by adapterProperty(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageLoader,
            onCLick
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    class ViewHolder(
        private val binding: ItemArticleBinding,
        private val imageLoader: ImageLoader,
        private val onCLick: (item: Article) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            with(binding) {
                this.root.setOnClickListener { onCLick(item) }
                articleTitleTxt.text = item.title
                articleDescTxt.text = item.abstract
                val media = item.media.first { it.type == "image" }
                imageLoader.loadImage(binding.articleImg, media.mediaMetadata[1].url)
            }
        }
    }
}
