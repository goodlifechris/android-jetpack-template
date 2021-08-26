package app.tendo.data

/**
 * Created by goodlife on 26,August,2021
 */

import app.tendo.data.local.db.TendoDao
import app.tendo.data.remote.TendoRemoteDataSource
import app.tendo.model.MovieDesc
import app.tendo.model.Result
import app.tendo.model.TrendingMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository which fetches data from Remote or Local data sources
 */
class MovieRepository @Inject constructor(
    private val tendoRemoteDataSource: TendoRemoteDataSource,
    private val tendoDao: TendoDao
) {

    suspend fun fetchTrendingMovies(): Flow<Result<TrendingMovieResponse>?> {
        return flow {
            emit(fetchTrendingMoviesCached())
            emit(Result.loading())
            val result = tendoRemoteDataSource.fetchTrendingMovies()

            //Cache to database if response is successful
            if (result.status == Result.Status.SUCCESS) {
                result.data?.results?.let { it ->
                    tendoDao.deleteAll(it)
                    tendoDao.insertAll(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchTrendingMoviesCached(): Result<TrendingMovieResponse>? =
            tendoDao.getAll()?.let {
                Result.success(TrendingMovieResponse(it))
            }

    suspend fun fetchMovie(id: Int): Flow<Result<MovieDesc>> {
        return flow {
            emit(Result.loading())
            emit(tendoRemoteDataSource.fetchMovie(id))
        }.flowOn(Dispatchers.IO)
    }
}