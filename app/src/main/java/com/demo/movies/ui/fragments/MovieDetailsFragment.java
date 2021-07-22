package com.demo.movies.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.demo.movies.R;
import com.demo.movies.data.models.MovieList;
import com.demo.movies.databinding.FragmentMovieDetailBinding;
import com.demo.movies.util.Common;


/**
 * Class to show movie details page
 */
public class MovieDetailsFragment extends Fragment {

    MovieList.MovieListItem moviedetails;
    FragmentMovieDetailBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        mBinding = FragmentMovieDetailBinding.bind(view);
        initView();
        return view;
    }

    public void setMovieInfo(MovieList.MovieListItem movieListItem) {
        this.moviedetails = movieListItem;
    }

    private void initView() {
        if(moviedetails.getShow().getImage()!=null){
            Glide.with(this).load(moviedetails.getShow().getImage().getOriginal())
                    .placeholder(R.mipmap.ic_launcher_background)
                    .error(R.mipmap.ic_launcher_background)
                    .into(mBinding.moviePoster);
        }
        mBinding.detailHeader.detailHeaderTitle.setText(moviedetails.getShow().getName());
        mBinding.detailHeader.type.setText(String.format("%s %s", getResources().getString(R.string.type), moviedetails.getShow().getType()));
        mBinding.detailHeader.release.setText(String.format("%s %s", getResources().getString(R.string.premiered), moviedetails.getShow().getPremiered()));
        mBinding.detailHeader.language.setText(String.format("%s %s", getResources().getString(R.string.language), moviedetails.getShow().getLanguage()));
        mBinding.detailBodySummary.setText(Common.summary(moviedetails.getShow().getSummary()));
    }

}