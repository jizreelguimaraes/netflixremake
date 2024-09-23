package br.com.jizreelguimaras.netflixremake.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jizreelguimaras.netflixremake.model.Category
import co.tiagoaguiar.netflixremake.R

class CategoryAdapter(
    private val categories: List<Category>,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val movie = categories[position]
        holder.bind(movie)
    }

    fun addCategories(categories: List<Category>){

        this.categories.toMutableList().addAll(categories)
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category) {

            val txtTitle : TextView = itemView.findViewById(R.id.txt_title)
            txtTitle.text = category.title

            val rvCategory: RecyclerView = itemView.findViewById(R.id.rv_category)

            rvCategory.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            rvCategory.adapter = MovieAdapter(category.movie, R.layout.movie_item, onItemClickListener)
        }
    }
}