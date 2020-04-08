package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.ResponseSummaryList
import io.ktor.http.Url

data class SummaryList<T : Any>(
    val available: Int,
    val returned: Int,
    val collectionUri: Url,
    val items: List<T>
)

fun <T : ResponseSummaryList.Summary, R : Any> ResponseSummaryList<T>.toSummaryList(
    translator: (T) -> R
) = SummaryList<R>(
    available = available,
    returned = returned,
    collectionUri = Url(collectionURI),
    items = items.map { translator(it) }
)
