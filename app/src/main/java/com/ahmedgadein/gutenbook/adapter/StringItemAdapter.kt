package com.ahmedgadein.gutenbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedgadein.gutenbook.databinding.StringItemListBinding

class StringItemsAdapter : ListAdapter<String, RecyclerView.ViewHolder>(StringDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StringHolder(
            StringItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val language = getItem(position)
        (holder as StringHolder).bind(language)
    }

    class StringHolder(private val binding: StringItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: String) {
            binding.language.text = language
        }
    }
}

class StringDiffUtils : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
