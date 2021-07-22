package com.demo.movies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.demo.movies.R
import com.demo.movies.data.models.MovieList.MovieListItem
import com.demo.movies.databinding.FragmentMovieDetailBinding
import com.demo.movies.util.Common.summary

/**
 * Class to show movie details page
 */
class MovieDetailsFragment : Fragment() {
    private var moviedetails: MovieListItem? = null
    private var mBinding: FragmentMovieDetailBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        mBinding = FragmentMovieDetailBinding.bind(view)
        initView()
        return view
    }

    fun setMovieInfo(movieListItem: MovieListItem?) {
        moviedetails = movieListItem
    }

    private fun initView() {
        if (moviedetails!!.show.image != null) {
            Glide.with(this).load(moviedetails!!.show.image.original)
                .placeholder(R.mipmap.ic_launcher_background)
                .error(R.mipmap.ic_launcher_background)
                .into(mBinding!!.moviePoster)
        }
        mBinding!!.detailHeader.detailHeaderTitle.text = moviedetails!!.show.name
        mBinding!!.detailHeader.type.text =
            String.format("%s %s", resources.getString(R.string.type), moviedetails!!.show.type)
        mBinding!!.detailHeader.release.text = String.format(
            "%s %s",
            resources.getString(R.string.premiered),
            moviedetails!!.show.premiered
        )
        mBinding!!.detailHeader.language.text = String.format(
            "%s %s",
            resources.getString(R.string.language),
            moviedetails!!.show.language
        )
        mBinding!!.detailBodySummary.text = summary(moviedetails!!.show.summary)
    }
}