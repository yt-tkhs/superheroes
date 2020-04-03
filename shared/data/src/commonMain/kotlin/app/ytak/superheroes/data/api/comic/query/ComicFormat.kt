package app.ytak.superheroes.data.api.comic.query

enum class ComicFormat(val code: String) {
    Comic("comic"),
    Magazine("mmagazine"),
    TradePaperback("trade paperback"),
    Hardcover("hardcover"),
    Digest("digest"),
    GraphicNovel("graphic novel"),
    DigitalComic("digital comic"),
    InfiniteComic("infinite comic"),
}
