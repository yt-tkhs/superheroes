package app.ytak.superheroes.data.model

import app.ytak.superheroes.data.dto.ResponseTextObject
import app.ytak.superheroes.data.type.TextObjectType

data class TextObject(
    val type: TextObjectType,
    val language: String,
    val text: String
)

fun ResponseTextObject.toTextObject() = TextObject(
    type = TextObjectType.from(type),
    language = language,
    text = text
)
