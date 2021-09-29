package com.example.movieapp.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ActorItemBinding
import com.example.movieapp.entities.Actor

class ActorsAdapter(
    private val actors: List<Actor>
) : RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val binding = ActorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    inner class ActorViewHolder(
        val binding: ActorItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            binding.actorName.text = actor.name
        }
    }

}