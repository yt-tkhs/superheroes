package app.ytak.superheroes.data.repository

import app.ytak.superheroes.data.api.OrderBy
import app.ytak.superheroes.data.api.comic.ComicApi
import app.ytak.superheroes.data.api.comic.query.*
import app.ytak.superheroes.data.model.Comic
import app.ytak.superheroes.data.model.Id
import app.ytak.superheroes.data.model.toComic
import com.soywiz.klock.DateTime
import com.soywiz.klock.MonthSpan
import com.soywiz.klock.plus

interface ComicRepository {

    suspend fun fetchComicsOnsaleThisMonth(
        offset: Int
    ): List<Comic>

    suspend fun fetchComicsOnsaleFuture(
        offset: Int
    ): List<Comic>

    suspend fun fetchComic(
        comicId: Id.Comic
    ): Comic?
}

internal class ComicRepositoryImpl(
    private val comicApi: ComicApi
) : ComicRepository {

    private val comics = mutableMapOf<Id.Comic, Comic>()

    override suspend fun fetchComicsOnsaleThisMonth(offset: Int): List<Comic> {
        val response = comicApi.fetchComics(
            format = ComicFormat.Comic,
            formatType = ComicFormatType.Comic,
            noVariants = true,
            dateDescriptor = DateDescriptor.ThisMonth,
            limit = 30,
            offset = offset,
            orderBy = OrderBy(OrderByAttribute.OnsaleDate).desc()
        )
        return response.data.results.map { it.toComic() }.withCache()
    }

    override suspend fun fetchComicsOnsaleFuture(offset: Int): List<Comic> {
        val now = DateTime.now().date
        val response = comicApi.fetchComics(
            format = ComicFormat.Comic,
            formatType = ComicFormatType.Comic,
            noVariants = true,
            dateRange = DateRange(from = now, to = now.plus(MonthSpan(1))),
            limit = 5,
            offset = offset,
            orderBy = OrderBy(OrderByAttribute.OnsaleDate).desc()
        )
        return response.data.results.map { it.toComic() }.withCache()
    }

    override suspend fun fetchComic(comicId: Id.Comic): Comic? {
        return comics[comicId] ?: comicApi.fetchComic(comicId).data.results.firstOrNull()?.toComic()
    }

    private fun List<Comic>.withCache() = apply {
        forEach { comic -> comics[comic.id] = comic }
    }
}
