package com.ahmedgadein.gutenbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedgadein.gutenbook.data.models.Person
import com.ahmedgadein.gutenbook.databinding.PersonItemListBinding

class PersonAdapter : ListAdapter<Person, RecyclerView.ViewHolder>(PersonDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonHolder(
            PersonItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val person = getItem(position)
        (holder as PersonHolder).bind(person)
    }

    class PersonHolder(private val binding: PersonItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.personName.text = person.name
            binding.birthAndDeathYears.text =
                String.format("%d - %d", person.birthYear, person.deathYear)
        }
    }
}

class PersonDiffUtils : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.name == newItem.name
    }
}
