@file:Suppress("RemoveExplicitTypeArguments")

package app.ytak.superheroes.data.di

import app.ytak.superheroes.data.HttpClientProvider
import app.ytak.superheroes.data.api.ComicApi
import app.ytak.superheroes.data.api.ComicApiImpl
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
