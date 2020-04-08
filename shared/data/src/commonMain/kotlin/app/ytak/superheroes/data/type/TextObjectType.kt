package app.ytak.superheroes.data.type

enum class TextObjectType(val code: String) {
    IssueSolicitText("issue_solicit_text"),
    IssuePreviewText("issue_preview_text"),
    ;

    companion object {
        private val codeMap by lazy { values().associateBy(TextObjectType::code) }

        fun from(code: String) = codeMap.getValue(code)
    }
}
