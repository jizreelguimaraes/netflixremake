package br.com.jizreelguimaras.netflixremake.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import br.com.jizreelguimaras.netflixremake.model.Movie
import co.tiagoaguiar.netflixremake.R
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val movies: List<Movie>,
    @LayoutRes private val layoutId: Int,
    private val onItemClickListener: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movies[position]
        holder.bind(movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {

            val imageCover: ImageView = itemView.findViewById(R.id.img_cover)
            imageCover.setOnClickListener {

                onItemClickListener?.invoke(movie.id)
            }

            Picasso.get().load(movie.coverUrl).into(imageCover)
        }
    }
}