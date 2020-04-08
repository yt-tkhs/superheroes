package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.ResponseUrl
import app.ytak.superheroes.data.type.UrlType
import io.ktor.http.Url

data class TypeUrl(
    val type: UrlType,
    val url: Url
)

fun ResponseUrl.toTypeUrl() = TypeUrl(
    type = UrlType.from(type),
    url = Url(url)
)
