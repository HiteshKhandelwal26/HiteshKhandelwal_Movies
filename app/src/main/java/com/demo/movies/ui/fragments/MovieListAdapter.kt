package com.demo.movies.ui.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.movies.R
import com.demo.movies.data.models.MovieList
import com.demo.movies.databinding.ItemMovieListBinding

/**
 * Setting up the Recycler view - Class defines adapter for Movies list
 */
class MovieListAdapter(
    private val dataset: MovieList?,
    private val context: Context,
    private val actionListener: ActionListener
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    interface ActionListener {
        fun onMovieSelected(position: Int)
    }

    class ViewHolder(var binding: ItemMovieListBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        init {
            binding.movieName.setOnClickListener { }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val binding: ItemMovieListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_movie_list, viewGroup, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.movieName.text = dataset!![position].show.name
        holder.binding.type.text = String.format(
            "%s %s",
            context.resources.getString(R.string.type),
            dataset[position].show.type
        )
        holder.binding.status.text = String.format(
            "%s : %s",
            context.resources.getString(R.string.status),
            dataset[position].show.status
        )
        if (dataset[position].show.image != null) {
            val url = dataset[position].show.image.original
            Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.binding.imageView)
        }

        holder.binding.mainLayout.setOnClickListener { view: View? ->
            actionListener.onMovieSelected(
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
}
