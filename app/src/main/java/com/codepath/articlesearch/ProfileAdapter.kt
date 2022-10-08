package com.codepath.articlesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_PROFILE = "MOVIE_PROFILE"
private const val TAG = "ProfileAdapter"

class ProfileAdapter(private val context: Context, private val portfolio: List<Work>) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val movie = portfolio[position]
        holder.bind(movie)
    }

    override fun getItemCount() = portfolio.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        //TODO: Return On Click Listener
        //View.OnClickListener


        private val moviePoster = itemView.findViewById<ImageView>(R.id.poster_image)
        private val  movieTitle = itemView.findViewById<TextView>(R.id.movie_title)
        private val  movieDescription = itemView.findViewById<TextView>(R.id.movie_description)


        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(movie: Work) {
            movieTitle.text = movie.title
            movieDescription.text = movie.overview

            Glide.with(context)
                .load(movie.posterImageUrl)
                .into(moviePoster)
        }
    }
}