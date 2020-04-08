package app.ytak.superheroes.data.model

/**
 * This class represents ID of models.
 */
sealed class Id<T : Any>(open val value: T) {

    data class Comic(override val value: Int) : Id<Int>(value)

    val longHashCode
        get() = value.hashCode().toLong()
}
