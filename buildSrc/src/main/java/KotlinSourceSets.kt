import org.gradle.api.NamedDomainObjectContainer
import org.gradle.kotlin.dsl.invoke

fun <T> NamedDomainObjectContainer<T>.androidMain(action: T.() -> Unit) {
    named("androidMain").invoke(action)
}

fun <T> NamedDomainObjectContainer<T>.iosMain(action: T.() -> Unit) {
    named("iosMain").invoke(action)
}
