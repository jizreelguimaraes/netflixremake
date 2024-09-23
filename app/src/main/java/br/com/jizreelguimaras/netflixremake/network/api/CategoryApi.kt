package br.com.jizreelguimaras.netflixremake.network.api

import br.com.jizreelguimaras.netflixremake.model.MovieDetail
import br.com.jizreelguimaras.netflixremake.model.dto.CategoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryApi {

    @GET("home")
    suspend fun getCategories(@Query("apiKey") apiKey: String) : Response<CategoryDto>

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int?, @Query("apiKey") apiKey: String) : Response<MovieDetail>
}