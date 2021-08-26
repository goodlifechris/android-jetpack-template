package app.tendo.data.local.db

/**
 * Created by goodlife on 26,August,2021
 */

import androidx.room.*
import app.tendo.data.local.db.entities.Movie

@Dao
interface TendoDao {

    @Query("SELECT * FROM movie order by popularity DESC")
    fun getAll(): List<Movie>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Delete
    fun delete(movie: Movie)

    @Delete
    fun deleteAll(movie: List<Movie>)
}