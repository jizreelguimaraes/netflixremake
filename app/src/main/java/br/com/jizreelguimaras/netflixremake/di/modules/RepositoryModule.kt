package br.com.jizreelguimaras.netflixremake.di.modules

import br.com.jizreelguimaras.netflixremake.network.api.CategoryApi
import br.com.jizreelguimaras.netflixremake.repository.CategoryRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { provideCategoryRepository(get()) }
}

fun provideCategoryRepository(
    categoryApi: CategoryApi
): CategoryRepository {

    return CategoryRepository(
        categoryApi = categoryApi
    )
}