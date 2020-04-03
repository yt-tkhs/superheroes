package app.ytak.superheroes.data

import io.ktor.client.HttpClient

expect object HttpClientProvider {

    fun create(): HttpClient
}
