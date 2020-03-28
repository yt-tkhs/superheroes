@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.ytak.superheroes.data

import com.soywiz.klock.DateTime
import com.soywiz.krypto.MD5
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.takeFrom
import kotlinx.io.core.toByteArray

fun HttpRequestBuilder.marvelUrl(path: String) {
    url {
        takeFrom("https://gateway.marvel.com/")
        encodedPath = "v1/public/$path"
    }
}

fun HttpRequestBuilder.authorized() {
    val ts = DateTime.nowUnixLong()
    val hash = MD5.digest("$ts${BuildKonfig.MARVEL_PRIVATE_KEY}${BuildKonfig.MARVEL_PUBLIC_KEY}".toByteArray())
        .toUByteArray()
        .joinToString("") { it.toString(16).padStart(2, '0') }
    parameter("apikey", BuildKonfig.MARVEL_PUBLIC_KEY)
    parameter("ts", ts)
    parameter("hash", hash)
}
