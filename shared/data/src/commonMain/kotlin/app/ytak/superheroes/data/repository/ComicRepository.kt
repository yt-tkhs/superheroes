package app.ytak.superheroes.data.repository

import app.ytak.superheroes.data.api.OrderBy
import app.ytak.superheroes.data.api.comic.ComicApi
import app.ytak.superheroes.data.api.comic.query.ComicFormat
import app.ytak.superheroes.data.api.comic.query.ComicFormatType
import app.ytak.superheroes.data.api.comic.query.DateDescriptor
import app.ytak.superheroes.data.dto.Comic

interface ComicRepository {

    suspend fun fetchComicsOnsaleThisMonth(
        offset: Int
    ): List<Comic>

    suspend fun fetchComicsOnsaleThisWeek(
        offset: Int
    ): List<Comic>
}

internal class ComicRepositoryImpl(
    private val comicApi: ComicApi
) : ComicRepository {

    override suspend fun fetchComicsOnsaleThisMonth(offset: Int): List<Comic> {
        val response = comicApi.fetchComics(
            format = ComicFormat.Comic,
            formatType = ComicFormatType.Comic,
            noVariants = true,
            dateDescriptor = DateDescriptor.ThisMonth,
            limit = 30,
            offset = offset,
            orderBy = OrderBy(ComicApi.OrderByAttribute.OnsaleDate).desc()
        )
        return response.data.results
    }

    override suspend fun fetchComicsOnsaleThisWeek(offset: Int): List<Comic> {
        val response = comicApi.fetchComics(
            format = ComicFormat.Comic,
            formatType = ComicFormatType.Comic,
            noVariants = true,
            dateDescriptor = DateDescriptor.NextWeek,
            limit = 5,
            offset = offset,
            orderBy = OrderBy(ComicApi.OrderByAttribute.OnsaleDate).desc()
        )
        return response.data.results
    }
}
