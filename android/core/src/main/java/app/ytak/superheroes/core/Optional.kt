package app.ytak.superheroes.core

data class Optional<T : Any>(private val value: T?) {

    companion object {

        fun <T : Any> of(value: T) = Optional(value)

        fun <T : Any> ofNullable(value: T?) = Optional(value)
    }

    fun getOrNull() = value
}
