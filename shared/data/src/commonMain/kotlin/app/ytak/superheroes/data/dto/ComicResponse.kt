package app.ytak.superheroes.data.dto

import app.ytak.superheroes.data.serializer.DateTimeTzSerializer
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.Serializable

@Serializable
data class ComicResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: ComicDataContainer,
    val etag: String
)

@Serializable
data class ComicDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comic>
)

@Serializable
data class Comic(
    val id: Int,
    val digitalId: Int,
    val title: String,
    val issueNumber: Double,
    val variantDescription: String,
    val description: String?,
    @Serializable(DateTimeTzSerializer::class)
    val modified: DateTimeTz,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Int,
    val textObjects: List<TextObject>,
    val resourceURI: String,
    val urls: List<Url>,
    val series: SeriesSummary,
    val variants: List<ComicSummary>,
    val collections: List<ComicSummary>,
    val collectedIssues: List<ComicSummary>,
    val dates: List<ComicDate>,
    val prices: List<ComicPrice>,
    val thumbnail: Image,
    val images: List<Image>,
    val creators: Sublist<CreatorSummary>,
    val characters: Sublist<CharacterSummary>,
    val stories: Sublist<StorySummary>,
    val events: Sublist<EventSummary>
)

@Serializable
data class TextObject(
    val type: String,
    val language: String,
    val text: String
)

@Serializable
data class Url(
    val type: String,
    val url: String
)

@Serializable
data class SeriesSummary(
    val resourceURI: String,
    val name: String
)

@Serializable
data class ComicSummary(
    val resourceURI: String,
    val name: String
)

@Serializable
data class ComicDate(
    val type: String,

    @Serializable(DateTimeTzSerializer::class)
    val date: DateTimeTz
)

@Serializable
data class ComicPrice(
    val type: String,
    val price: Float
)

@Serializable
data class Sublist<T : Sublist.Summary>(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<T>
) {
    interface Summary
}

@Serializable
data class Image(
    val path: String,
    val extension: String
)

@Serializable
data class CreatorSummary(
    val resourceURI: String,
    val name: String,
    val role: String
) : Sublist.Summary

@Serializable
data class CharacterSummary(
    val resourceURI: String,
    val name: String,
    val role: String? = null
) : Sublist.Summary

@Serializable
data class StorySummary(
    val resourceURI: String,
    val name: String,
    val type: String
) : Sublist.Summary

@Serializable
data class EventSummary(
    val resourceURI: String,
    val name: String
) : Sublist.Summary
