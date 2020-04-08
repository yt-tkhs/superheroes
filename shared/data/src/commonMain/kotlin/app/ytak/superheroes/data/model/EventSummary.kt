package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.ResponseEventSummary
import io.ktor.http.Url

data class EventSummary(
    val resourceUri: Url,
    val name: String
)

fun ResponseEventSummary.toEventSummary() = EventSummary(
    resourceUri = Url(resourceURI),
    name = name
)
