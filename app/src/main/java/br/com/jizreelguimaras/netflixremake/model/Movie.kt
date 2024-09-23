package br.com.jizreelguimaras.netflixremake.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("cover_url")
    val coverUrl: String
)
