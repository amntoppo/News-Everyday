package io.aman.newseveryday.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import io.aman.newseveryday.R
import io.aman.newseveryday.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentCategoryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.healthButton.setOnClickListener(this)
        binding.entertainmentButton.setOnClickListener(this)
        binding.sportsButton.setOnClickListener(this)
        binding.worldButton.setOnClickListener(this)
        binding.politicsButton.setOnClickListener(this)
        binding.stockButton.setOnClickListener(this)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        val button: Button = view as Button
        showFragment(button.text as String)
    }

    fun showFragment(category: String){
            val bundle = Bundle()
            bundle.putString("category", category)
            activity?.findNavController(R.id.nav_host_fragment_activity_main)?.navigate(R.id.categoryNewsFragment, bundle)

    }
}