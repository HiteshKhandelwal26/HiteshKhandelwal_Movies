package com.demo.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.demo.movies.base.BaseActivity
import com.demo.movies.data.models.MovieList
import com.demo.movies.ui.fragments.DashboardFragment
import com.demo.movies.ui.fragments.MovieDetailsFragment

@Suppress("UNREACHABLE_CODE")
class MainActivity : BaseActivity(), DashboardFragment.ActionListener {
    var prevFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDefaultScreen()
    }
    /*Loads the Dashboard screen as the default page*/
    private fun setDefaultScreen() {
        val fragment: Fragment = DashboardFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentHolderFullScreen, fragment)
            .commit()
    }
    /*On tapping any of the movie from the list item- opens the details page*/
    override fun onMovieSelected(movieListItem: MovieList.MovieListItem?) {
        val fragment: Fragment = MovieDetailsFragment()
        (fragment as MovieDetailsFragment).setMovieInfo(movieListItem)
        setSelectedFragment(fragment)
    }
    /*Sets the selected fragment to load*/
    private fun setSelectedFragment(fragment: Fragment?) {
        if (fragment != null) {
            val fragments: List<*> = supportFragmentManager.fragments
            prevFragment = fragments[fragments.size - 1] as Fragment
            supportFragmentManager.beginTransaction()
                .hide(prevFragment!!)
                .add(R.id.fragmentHolderFullScreen, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
        }
    }
}
