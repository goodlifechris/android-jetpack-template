package io.buildwithnd.demotmdb.ui.listing
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tendo.data.MovieRepository
import app.tendo.model.TrendingMovieResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import app.tendo.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * ViewModel for ListingActivity
 */
@HiltViewModel
class ListingViewModel @Inject constructor(private val movieRepository: MovieRepository) :
        ViewModel() {

    private val _movieList = MutableLiveData<Result<TrendingMovieResponse>>()
    val movieList = _movieList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.fetchTrendingMovies().collect {
                _movieList.value = it
            }
        }
    }
}