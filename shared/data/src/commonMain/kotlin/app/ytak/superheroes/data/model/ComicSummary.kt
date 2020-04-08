package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.ResponseComicSummary
import io.ktor.http.Url

data class ComicSummary(
    val resourceUri: Url,
    val name: String
)

fun ResponseComicSummary.toComicSummary() = ComicSummary(
    resourceUri = Url(resourceURI),
    name = name
)
