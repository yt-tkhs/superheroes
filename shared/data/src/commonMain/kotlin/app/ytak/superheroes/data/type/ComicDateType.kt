package app.ytak.superheroes.data.type

enum class ComicDateType(val code: String) {
    Onsale("onsaleDate"),
    Foc("focDate"),
    Unlimited("unlimitedDate"),
    DigitalPurchase("digitalPurchaseDate"),
    ;

    companion object {
        private val codeMap by lazy { values().associateBy(ComicDateType::code) }

        fun from(code: String) = codeMap.getValue(code)
    }
}
