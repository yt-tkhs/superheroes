package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.*
import app.ytak.superheroes.data.type.ComicDateType
import app.ytak.superheroes.data.type.ComicFormat
import app.ytak.superheroes.data.type.ComicPriceType
import com.soywiz.klock.DateTimeTz
import io.ktor.http.Url

data class Comic(
    val id: Id.Comic,
    val digitalId: Int,
    val title: String,
    val issueNumber: Double,
    val variantDescription: String,
    val description: String?,
    val modified: DateTimeTz,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: ComicFormat,
    val pageCount: Int,
    val textObjects: List<TextObject>,
    val resourceUri: Url,
    val urls: List<TypeUrl>,
    val series: SeriesSummary,
    val variants: List<ComicSummary>,
    val collections: List<ComicSummary>,
    val collectedIssues: List<ComicSummary>,
    val dates: List<ComicDate>,
    val prices: List<ComicPrice>,
    val thumbnail: Url,
    val images: List<Url>,
    val creators: SummaryList<CreatorSummary>,
    val characters: SummaryList<CharacterSummary>,
    val stories: SummaryList<StorySummary>,
    val events: SummaryList<EventSummary>
) {

    data class ComicDate(
        val type: ComicDateType,
        val date: DateTimeTz
    )

    data class ComicPrice(
        val type: ComicPriceType,
        val price: Float
    )
}

fun ResponseComic.toComic() = Comic(
    id = Id.Comic(id),
    digitalId = digitalId,
    title = title,
    issueNumber = issueNumber,
    variantDescription = variantDescription,
    description = description,
    modified = modified,
    isbn = isbn,
    upc = upc,
    diamondCode = diamondCode,
    ean = ean,
    issn = issn,
    format = ComicFormat.from(format),
    pageCount = pageCount,
    textObjects = textObjects.map(ResponseTextObject::toTextObject),
    resourceUri = Url(resourceURI),
    urls = urls.map(ResponseUrl::toTypeUrl),
    series = series.toSeriesSummary(),
    variants = variants.map(ResponseComicSummary::toComicSummary),
    collections = collections.map(ResponseComicSummary::toComicSummary),
    collectedIssues = collectedIssues.map(ResponseComicSummary::toComicSummary),
    dates = dates.map(ResponseComicDate::toComicDate),
    prices = prices.map(ResponseComicPrice::toComicPrice),
    thumbnail = thumbnail.toUrl(),
    images = images.map(ResponseImage::toUrl),
    creators = creators.toSummaryList(ResponseCreatorSummary::toCreatorSummary),
    characters = characters.toSummaryList(ResponseCharacterSummary::toCharacterSummary),
    stories = stories.toSummaryList(ResponseStorySummary::toStorySummary),
    events = events.toSummaryList(ResponseEventSummary::toEventSummary)
)

fun ResponseComicDate.toComicDate() = Comic.ComicDate(
    type = ComicDateType.from(type),
    date = date
)

fun ResponseComicPrice.toComicPrice() = Comic.ComicPrice(
    type = ComicPriceType.from(type),
    price = price
)

fun ResponseImage.toUrl() =
    Url("${path}.${extension}")
