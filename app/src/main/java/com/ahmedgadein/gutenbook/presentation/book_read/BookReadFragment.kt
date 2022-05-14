package com.ahmedgadein.gutenbook.presentation.book_read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ahmedgadein.gutenbook.databinding.BookReadFragmentBinding

class BookReadFragment : Fragment() {
    private lateinit var binding: BookReadFragmentBinding
    private val args: BookReadFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookReadFragmentBinding.inflate(inflater, container, false)
        setUI()
        return binding.root
    }

    private fun setUI() {
        binding.content = args.content
        binding.title = args.title
        binding.webiew.apply {
            webViewClient = WebViewClient()
            loadUrl(args.content)
        }
    }
}
