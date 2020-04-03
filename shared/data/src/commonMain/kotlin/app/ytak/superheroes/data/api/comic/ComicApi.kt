package app.ytak.superheroes.data.api.comic

import app.ytak.superheroes.data.api.OrderBy
import app.ytak.superheroes.data.api.comic.query.ComicFormat
import app.ytak.superheroes.data.api.comic.query.ComicFormatType
import app.ytak.superheroes.data.api.comic.query.DateDescriptor
import app.ytak.superheroes.data.authorized
import app.ytak.superheroes.data.dto.ComicResponse
import app.ytak.superheroes.data.marvelUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface ComicApi {

    suspend fun fetchComics(
        format: ComicFormat? = null,
        formatType: ComicFormatType? = null,
        noVariants: Boolean? = null,
        dateDescriptor: DateDescriptor? = null,
        limit: Int? = null,
        offset: Int? = null,
        orderBy: OrderBy<OrderByAttribute>? = null
    ): ComicResponse

    enum class OrderByAttribute(override val keyName: String) : OrderBy.Attribute {
        FocDate("focDate"),
        OnsaleDate("onsaleDate"),
        Title("title"),
        IssueNumber("issueNumber"),
        Modified("modified")
    }
}

internal class ComicApiImpl(private val httpClient: HttpClient) : ComicApi {

    override suspend fun fetchComics(
        format: ComicFormat?,
        formatType: ComicFormatType?,
        noVariants: Boolean?,
        dateDescriptor: DateDescriptor?,
        limit: Int?,
        offset: Int?,
        orderBy: OrderBy<ComicApi.OrderByAttribute>?
    ): ComicResponse = httpClient.get {
        authorized()
        marvelUrl("comics")
        format?.let { parameter("format", it.code) }
        formatType?.let { parameter("formatType", it.code) }
        noVariants?.let { parameter("noVariants", it) }
        dateDescriptor?.let { parameter("dateDescriptor", it.code) }
        limit?.let { parameter("limit", it) }
        offset?.let { parameter("offset", it) }
        orderBy?.let { parameter("orderBy", it.code) }
    }
}
