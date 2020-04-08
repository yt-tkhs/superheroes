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
    val data: ResponseComicDataContainer,
    val etag: String
)

@Serializable
data class ResponseComicDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<ResponseComic>
)

@Serializable
data class ResponseComic(
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
    val textObjects: List<ResponseTextObject>,
    val resourceURI: String,
    val urls: List<ResponseUrl>,
    val series: ResponseSeriesSummary,
    val variants: List<ResponseComicSummary>,
    val collections: List<ResponseComicSummary>,
    val collectedIssues: List<ResponseComicSummary>,
    val dates: List<ResponseComicDate>,
    val prices: List<ResponseComicPrice>,
    val thumbnail: ResponseImage,
    val images: List<ResponseImage>,
    val creators: ResponseSummaryList<ResponseCreatorSummary>,
    val characters: ResponseSummaryList<ResponseCharacterSummary>,
    val stories: ResponseSummaryList<ResponseStorySummary>,
    val events: ResponseSummaryList<ResponseEventSummary>
)

@Serializable
data class ResponseTextObject(
    val type: String,
    val language: String,
    val text: String
)

@Serializable
data class ResponseUrl(
    val type: String,
    val url: String
)

@Serializable
data class ResponseSeriesSummary(
    val resourceURI: String,
    val name: String
)

@Serializable
data class ResponseComicSummary(
    val resourceURI: String,
    val name: String
)

@Serializable
data class ResponseComicDate(
    val type: String,

    @Serializable(DateTimeTzSerializer::class)
    val date: DateTimeTz
)

@Serializable
data class ResponseComicPrice(
    val type: String,
    val price: Float
)

@Serializable
data class ResponseSummaryList<T : ResponseSummaryList.Summary>(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<T>
) {
    interface Summary
}

@Serializable
data class ResponseImage(
    val path: String,
    val extension: String
)

@Serializable
data class ResponseCreatorSummary(
    val resourceURI: String,
    val name: String,
    val role: String
) : ResponseSummaryList.Summary

@Serializable
data class ResponseCharacterSummary(
    val resourceURI: String,
    val name: String,
    val role: String? = null
) : ResponseSummaryList.Summary

@Serializable
data class ResponseStorySummary(
    val resourceURI: String,
    val name: String,
    val type: String
) : ResponseSummaryList.Summary

@Serializable
data class ResponseEventSummary(
    val resourceURI: String,
    val name: String
) : ResponseSummaryList.Summary
