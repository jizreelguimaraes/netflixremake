package br.com.jizreelguimaras.netflixremake.di.modules

import br.com.jizreelguimaras.netflixremake.network.api.CategoryApi
import br.com.jizreelguimaras.netflixremake.network.utils.RetrofitBuilder.provideRetrofitBuilder
import org.koin.dsl.module

val apiModule = module {

    single { provideCategoryApi() }
}

fun provideCategoryApi(): CategoryApi {

    return provideRetrofitBuilder(
        "https://api.tiagoaguiar.co/netflixapp/",
        CategoryApi::class.java
    )
}

