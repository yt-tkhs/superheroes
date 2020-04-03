package app.ytak.superheroes.data.api

sealed class OrderBy<T : OrderBy.Attribute>(protected open val key: T) {

    companion object {

        operator fun <T : Attribute> invoke(key: T): Builder<T> =
            Builder(key)
    }

    data class Builder<T : Attribute>(private val key: T) {
        fun asc(): Asc<T> =
            Asc(key)
        fun desc(): Desc<T> =
            Desc(key)
    }

    interface Attribute {
        val keyName: String
    }

    abstract val code: String

    data class Asc<T : Attribute>(override val key: T) : OrderBy<T>(key) {
        override val code: String
            get() = key.keyName
    }

    data class Desc<T : Attribute>(override val key: T) : OrderBy<T>(key) {
        override val code: String
            get() = "-${key.keyName}"
    }
}
