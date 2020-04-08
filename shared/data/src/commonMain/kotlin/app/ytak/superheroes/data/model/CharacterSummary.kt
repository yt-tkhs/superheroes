package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.ResponseCharacterSummary
import io.ktor.http.Url

data class CharacterSummary(
    val resourceUri: Url,
    val name: String,
    val role: String?
)

fun ResponseCharacterSummary.toCharacterSummary() = CharacterSummary(
    resourceUri = Url(resourceURI),
    name = name,
    role = role
)
