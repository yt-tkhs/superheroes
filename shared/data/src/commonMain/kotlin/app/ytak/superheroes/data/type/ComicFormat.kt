package app.ytak.superheroes.data.type

enum class ComicFormat(val code: String) {
    Comic("Comic"),
    Magazine("Magazine"),
    TradePaperback("Trade Paperback"),
    Hardcover("Hardcover"),
    Digest("Digest"),
    GraphicNovel("Graphic Novel"),
    DigitalComic("Digital Comic"),
    InfiniteComic("Infinite Comic"),
    ;

    companion object {
        private val codeMap by lazy { values().associateBy(ComicFormat::code) }

        fun from(code: String) = codeMap.getValue(code)
    }
}
