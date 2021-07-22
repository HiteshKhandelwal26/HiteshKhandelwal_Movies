package com.demo.movies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.demo.movies.data.repo.MainRepository
import com.demo.movies.util.Resource
import kotlinx.coroutines.Dispatchers
/*Setting up the ViewModel class that acts as an intermediate between the View and Model*/
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getMovieList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getMovieList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
