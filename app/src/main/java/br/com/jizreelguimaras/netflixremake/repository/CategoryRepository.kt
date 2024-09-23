package br.com.jizreelguimaras.netflixremake.repository

import br.com.jizreelguimaras.netflixremake.model.MovieDetail
import br.com.jizreelguimaras.netflixremake.model.dto.CategoryDto
import br.com.jizreelguimaras.netflixremake.network.api.CategoryApi
import br.com.jizreelguimaras.netflixremake.network.utils.CallResult

class CategoryRepository(
    private val categoryApi: CategoryApi
) {

    suspend fun getCategories(): CallResult<CategoryDto>? {

        val apiKey = "96826e52-45bc-45df-898b-08db393a182a"

        try {
            categoryApi.getCategories(apiKey).let {

                if (it.isSuccessful) {

                    return CallResult.Success(it.body(), it.code())
                } else {

                    return CallResult.Failure(it.errorBody()?.string())
                }
            }
        } catch (ex: Exception) {

            return CallResult.Failure(ex.message)
        }
    }

    suspend fun getMovie(id: Int): CallResult<MovieDetail>? {

        val apiKey = "96826e52-45bc-45df-898b-08db393a182a"

        try {
            categoryApi.getMovie(id, apiKey).let {

                if (it.isSuccessful) {

                    return CallResult.Success(it.body(), it.code())
                } else {

                    return CallResult.Failure(it.errorBody()?.string())
                }
            }
        } catch (ex: Exception) {

            return CallResult.Failure(ex.message)
        }
    }
}