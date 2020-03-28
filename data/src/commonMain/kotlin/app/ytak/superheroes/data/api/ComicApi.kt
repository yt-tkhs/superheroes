package app.ytak.superheroes.data.api

import app.ytak.superheroes.data.api.query.DateDescriptor
import app.ytak.superheroes.data.api.query.OrderBy
import app.ytak.superheroes.data.dto.ComicResponse
import app.ytak.superheroes.data.marvelUrl
import app.ytak.superheroes.data.authorized
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface ComicApi {

    suspend fun fetchComics(
        dateDescriptor: DateDescriptor,
        limit: Int? = null,
        count: Int? = null,
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

class ComicApiImpl(private val httpClient: HttpClient) : ComicApi {

    override suspend fun fetchComics(
        dateDescriptor: DateDescriptor,
        limit: Int?,
        count: Int?,
        orderBy: OrderBy<ComicApi.OrderByAttribute>?
    ): ComicResponse = httpClient.get {
        authorized()
        marvelUrl("comics")
        parameter("dateDescriptor", dateDescriptor.code)
        limit?.let { parameter("limit", it) }
        count?.let { parameter("count", it) }
        orderBy?.let { parameter("orderBy", it.code) }
    }
}
