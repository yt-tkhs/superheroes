package app.ytak.superheroes.data.type

enum class UrlType(val code: String) {
    Detail("detail"),
    Purchase("purchase"),
    Unknown(""),
    ;

    companion object {
        private val codeMap by lazy { values().associateBy(UrlType::code) }

        fun from(code: String) = codeMap[code] ?: Unknown
    }
}
