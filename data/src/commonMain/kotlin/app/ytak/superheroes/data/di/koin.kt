@file:Suppress("RemoveExplicitTypeArguments")

package app.ytak.superheroes.data.di

import app.ytak.superheroes.data.HttpClientProvider
import app.ytak.superheroes.data.api.comic.ComicApi
import app.ytak.superheroes.data.api.comic.ComicApiImpl
import app.ytak.superheroes.data.repository.ComicRepository
import app.ytak.superheroes.data.repository.ComicRepositoryImpl
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> {
        HttpClientProvider.create()
    }
}

val apiModule = module {
    single<ComicApi> { ComicApiImpl(get()) }
}

val repositoryModule = module {
    single<ComicRepository> { ComicRepositoryImpl(get()) }
}
