package br.com.jizreelguimaras.netflixremake.di

import br.com.jizreelguimaras.netflixremake.di.modules.apiModule
import br.com.jizreelguimaras.netflixremake.di.modules.repositoryModule

val appModules = listOf(
    apiModule,
    repositoryModule
)