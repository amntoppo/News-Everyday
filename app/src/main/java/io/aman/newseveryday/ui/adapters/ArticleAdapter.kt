package io.aman.newseveryday.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.aman.newseveryday.databinding.ArticleListItemWithImageBinding
import io.aman.newseveryday.model.Article

class ArticleAdapter: ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleListItemWithImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null) {
            holder.bind(currentItem)
            if(currentItem.urlToImage.isNullOrEmpty()) {
                holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)

            }

        }
    }

    class ArticleViewHolder(private val binding: ArticleListItemWithImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                    Glide.with(itemView)
                        .load(article.urlToImage)
                        .into(newsImageView)
                newsHeading.text = article.title
                if(article.description.isNullOrEmpty())
                    newsDescription.visibility = View.GONE
                else {
                    newsDescription.visibility = View.VISIBLE
                    newsDescription.text = article.description
                }
                newsContent.visibility = View.GONE
                if(article.author.isNullOrEmpty())
                    newsAuthor.visibility = View.GONE
                else {
                    newsAuthor.visibility = View.VISIBLE
                    newsAuthor.text = article.author
                }
            }
        }
    }

    class ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }

        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.title == newItem.title


        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem
    }
}