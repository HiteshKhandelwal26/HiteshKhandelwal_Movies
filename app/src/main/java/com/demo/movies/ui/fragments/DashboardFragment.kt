package com.demo.movies.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.movies.R
import com.demo.movies.apis.ApiHelper
import com.demo.movies.apis.BaseRetroFitManager
import com.demo.movies.data.models.MovieList
import com.demo.movies.data.models.MovieList.MovieListItem
import com.demo.movies.databinding.FragmentDashboardBinding
import com.demo.movies.ui.viewmodel.MainViewModel
import com.demo.movies.ui.viewmodel.ViewModelFactory
import com.demo.movies.util.Common
import com.demo.movies.util.Status
import com.google.android.material.snackbar.Snackbar
/*Setting up the view class - the dashboard that loads the list of movies from the API response*/
class DashboardFragment : Fragment(), MovieListAdapter.ActionListener {
    var mBinding: FragmentDashboardBinding? = null
    private var HorizontalLayout: LinearLayoutManager? = null
    private var RecyclerViewLayoutManager: RecyclerView.LayoutManager? = null
    var mSnackBar: Snackbar? = null
    var movieListItems: MovieList? = null
    var mListener: ActionListener? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObservers()
    }
    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(BaseRetroFitManager.API_SERVICE))
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers() {
        if (activity?.let { Common.isNetworkConnected(it) }!!) {
            viewModel.getMovieList().observe(
                viewLifecycleOwner,
                {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                mBinding!!.txtNoData.visibility = View.GONE
                                mBinding!!.listView.visibility = View.VISIBLE
                                mBinding!!.progressBarPage.visibility = View.GONE
                                resource.data?.let { movieList -> initView(movieList) }
                            }
                            Status.ERROR -> {
                                mBinding!!.txtNoData.visibility = View.VISIBLE
                                mBinding!!.listView.visibility = View.GONE
                                mBinding!!.progressBarPage.visibility = View.GONE
                                mSnackBar = Common.getSnackBar(
                                    it.message,
                                    Snackbar.LENGTH_SHORT,
                                    mBinding!!.mainLayout
                                )
                                mSnackBar?.show()
                            }
                            Status.LOADING -> {
                                mBinding!!.progressBarPage.visibility = View.VISIBLE
                                mBinding!!.txtNoData.visibility = View.GONE
                                mBinding!!.listView.visibility = View.GONE
                            }
                        }
                    }
                }
            )
        } else {
            mSnackBar = Common.getSnackBar(
                resources.getString(R.string.noInternet),
                Snackbar.LENGTH_SHORT,
                mBinding!!.mainLayout
            )
            mSnackBar?.show()
            mBinding!!.listView.visibility = View.GONE
            mBinding!!.txtNoData.visibility = View.VISIBLE
        }
    }

    fun initView(listItems: MovieList?) {
        movieListItems = listItems
        RecyclerViewLayoutManager = LinearLayoutManager(activity)
        mBinding!!.listView.layoutManager = RecyclerViewLayoutManager
        mBinding!!.listView.visibility = View.VISIBLE
        HorizontalLayout = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        val adapter = activity?.let { MovieListAdapter(movieListItems, it, this) }
        mBinding!!.listView.layoutManager = HorizontalLayout
        mBinding!!.listView.adapter = adapter
    }
    /*Callback coming from the adapter - receiving the postion selected from the list*/
    override fun onMovieSelected(position: Int) {
        if (movieListItems!![position].show.status == resources.getString(R.string.indevelop)) {
            mSnackBar = Common.getSnackBar(
                resources.getString(R.string.under_develop),
                Snackbar.LENGTH_SHORT,
                mBinding!!.mainLayout
            )
            mSnackBar?.show()
        } else {
            /*Sending this information to the main activity*/
            mListener!!.onMovieSelected(movieListItems!![position])
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            context as ActionListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement onSomeEventListener")
        }
    }

    interface ActionListener {
        fun onMovieSelected(movieListItem: MovieListItem?)
    }
}
