package app.ytak.superheroes.core.di

import app.ytak.superheroes.data.di.apiModule
import app.ytak.superheroes.data.di.dataModule
import app.ytak.superheroes.data.di.repositoryModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        dataModule,
        apiModule,
        repositoryModule
    )
}
