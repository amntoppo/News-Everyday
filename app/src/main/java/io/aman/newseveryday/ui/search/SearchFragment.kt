package io.aman.newseveryday.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.aman.newseveryday.databinding.FragmentSearchBinding
import io.aman.newseveryday.ui.adapters.ArticleAdapter
import io.aman.newseveryday.utils.Resource

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val articleAdapter = ArticleAdapter()
        binding.apply {
            editText.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    // If the event is a key-down event on the "enter" button
                    if (event.getAction() === KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        viewModel.getSearchDataRepository(editText.text.toString()).observe(viewLifecycleOwner) { result ->
                            if(result.data.isNullOrEmpty()) {
                                searchViewError.visibility = VISIBLE
                            }
                            else {
                                searchViewError.visibility = GONE
                            }
                            articleAdapter.submitList(result.data)
                            progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                            textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                            textViewError.text = result.error?.localizedMessage
                        }
                        return true
                    }
                    return false
                }
            })

            searchRecyclerView.apply {
                adapter = articleAdapter
                layoutManager = LinearLayoutManager(this@SearchFragment.context)
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}