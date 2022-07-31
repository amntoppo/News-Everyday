package io.aman.newseveryday.ui.categoryNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.aman.newseveryday.databinding.FragmentCategorynewsBinding
import io.aman.newseveryday.ui.adapters.ArticleAdapter
import io.aman.newseveryday.utils.Resource

@AndroidEntryPoint
class CategoryNewsFragment: Fragment() {
    private var _binding: FragmentCategorynewsBinding? = null

    private val binding get() = _binding!!
    private var category: String? = null
    private val viewmodel: CategoryNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategorynewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val bundle: Bundle? = this.arguments
        category = bundle?.getString("category")
        val articleAdapter = ArticleAdapter()
        binding.apply {
            categoryRecyclerView.apply {
                adapter = articleAdapter
                layoutManager = LinearLayoutManager(this@CategoryNewsFragment.context)
            }

            viewmodel.getCategoryDataRepository(category!!).observe(viewLifecycleOwner) { result ->
                articleAdapter.submitList(result.data)
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }

        }

        return root
    }
}