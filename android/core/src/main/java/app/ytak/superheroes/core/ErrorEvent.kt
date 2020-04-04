package app.ytak.superheroes.core

data class ErrorEvent(
    val cause: Throwable,
    val recoverAction: (() -> Unit)? = null
) {

    enum class Kind {
        Network,
        Api,
        Unexpected,
    }
}
