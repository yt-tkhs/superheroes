package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.ResponseCreatorSummary
import io.ktor.http.Url

data class CreatorSummary(
    val resourceUri: Url,
    val name: String,
    val role: String
)

fun ResponseCreatorSummary.toCreatorSummary() = CreatorSummary(
    resourceUri = Url(resourceURI),
    name = name,
    role = role
)
