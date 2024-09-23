package br.com.jizreelguimaras.netflixremake.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val id: Int,
    @SerializedName("cover_url")
    val coverUrl: String,
    val title: String,
    val desc: String,
    val cast: String,
    val movie: List<Movie>
)
