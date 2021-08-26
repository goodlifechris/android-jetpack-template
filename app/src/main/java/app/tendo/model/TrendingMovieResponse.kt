package app.tendo.model

import app.tendo.data.local.db.entities.Movie

class TrendingMovieResponse(
    val results: List<Movie>?
)