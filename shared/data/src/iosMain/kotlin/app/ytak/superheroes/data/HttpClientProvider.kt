package app.ytak.superheroes.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.ios.Ios
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

actual object HttpClientProvider {
    actual fun create(): HttpClient = HttpClient(Ios) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
}
