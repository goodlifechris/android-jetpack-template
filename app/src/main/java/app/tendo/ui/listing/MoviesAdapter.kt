package app.tendo.ui.listing

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.tendo.Config
import app.tendo.R
import app.tendo.data.local.db.entities.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import app.tendo.ui.details.DetailsActivity
import app.tendo.util.Genre
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MoviesAdapter(private val context: Context, private val list: ArrayList<Movie>) :
        RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.EXTRAS_MOVIE_ID, movie.id)
                context.startActivity(intent)
            }
            itemView.tvTitle.text = movie.title
            Glide.with(context).load(Config.IMAGE_URL + movie.poster_path)
                    .apply(RequestOptions().override(400, 400).centerInside().placeholder(R.drawable.placehoder)).into(itemView.ivPoster)
            itemView.tvGenre.text = Genre.getGenre(movie.genre_ids)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(context, view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateData(newList: List<Movie>) {
        list.clear()
        val sortedList = newList.sortedBy { movie -> movie.popularity }
        list.addAll(sortedList)
        notifyDataSetChanged()
    }
}