package br.com.jizreelguimaras.netflixremake.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import br.com.jizreelguimaras.netflixremake.adapter.MovieAdapter
import br.com.jizreelguimaras.netflixremake.network.utils.CallResult
import br.com.jizreelguimaras.netflixremake.repository.CategoryRepository
import co.tiagoaguiar.netflixremake.R
import co.tiagoaguiar.netflixremake.databinding.ActivityMovieBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding
    private val categoryRepository: CategoryRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        intent.extras?.getInt("id")?.let { id ->

            lifecycleScope.launch {
                categoryRepository.getMovie(id)?.let {
                    when (it) {
                        is CallResult.Failure -> {

                        }

                        is CallResult.Success -> {

                            it.value?.let { movieDetail ->

                                Picasso.get().load(movieDetail.coverUrl).into(binding.movieImg)

                                binding.movieTxtTitle.text = movieDetail.title
                                binding.movieTxtDesc.text = movieDetail.desc
                                binding.movieTxtDesc.text = movieDetail.cast

                                binding.movieRvSimilar.layoutManager = GridLayoutManager(this@MovieActivity, 3)
                                binding.movieRvSimilar.adapter =
                                    MovieAdapter(movieDetail.movie, R.layout.movie_item_similar)

                                setSupportActionBar(binding.movieToolbar)

                                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
                                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                                supportActionBar?.title = null
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {

            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun newInstance(activity: Activity, id: Int) {
            Intent(activity, MovieActivity::class.java).apply {
                putExtra("id", id)
                activity.startActivity(this)
            }
        }
    }
}