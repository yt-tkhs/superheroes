package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.ResponseSeriesSummary
import io.ktor.http.Url

data class SeriesSummary(
    val resourceUri: Url,
    val name: String
)

fun ResponseSeriesSummary.toSeriesSummary() = SeriesSummary(
    resourceUri = Url(resourceURI),
    name = name
)
