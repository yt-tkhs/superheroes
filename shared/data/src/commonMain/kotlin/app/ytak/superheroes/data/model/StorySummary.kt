package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.ResponseStorySummary
import io.ktor.http.Url

data class StorySummary(
    val resourceURI: Url,
    val name: String,
    val type: String
)

fun ResponseStorySummary.toStorySummary() = StorySummary(
    resourceURI = Url(resourceURI),
    name = name,
    type = type
)
