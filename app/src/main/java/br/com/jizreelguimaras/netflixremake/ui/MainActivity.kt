package br.com.jizreelguimaras.netflixremake.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jizreelguimaras.netflixremake.adapter.CategoryAdapter
import br.com.jizreelguimaras.netflixremake.network.utils.CallResult
import br.com.jizreelguimaras.netflixremake.repository.CategoryRepository
import co.tiagoaguiar.netflixremake.R
import co.tiagoaguiar.netflixremake.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CategoryAdapter
    private val categoryRepository: CategoryRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.progressMain.visibility = View.VISIBLE
        binding.rvMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        carregaCategorias()
    }

    private fun carregaCategorias() {

        lifecycleScope.launch {
            categoryRepository.getCategories()?.let {
                when (it) {
                    is CallResult.Failure -> {

                        binding.progressMain.visibility = View.GONE
                    }

                    is CallResult.Success -> {

                        it.value?.category?.let { categories ->

                            adapter = CategoryAdapter(categories) { id ->

                                MovieActivity.newInstance(this@MainActivity, id)
                            }

                            binding.rvMain.adapter = adapter
                        }

                        binding.progressMain.visibility = View.GONE
                    }
                }
            }
        }
    }
}