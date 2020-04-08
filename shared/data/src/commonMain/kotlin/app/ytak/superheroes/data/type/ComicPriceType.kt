package app.ytak.superheroes.data.type

enum class ComicPriceType(val code: String) {
    Print("printPrice"),
    DigitalPurchase("digitalPurchasePrice"),
    ;

    companion object {
        private val codeMap by lazy { values().associateBy(ComicPriceType::code) }

        fun from(code: String) = codeMap.getValue(code)
    }
}
