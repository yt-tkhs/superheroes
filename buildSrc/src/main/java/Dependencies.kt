import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.kotlin.dsl.exclude

object Versions {
    const val minSdk = 21
    const val compileSdk = 29
    const val targetSdk = 29

    const val kotlin = "1.3.61"
    const val androidX = "1.1.0"
    const val androidDradlePlugin = "4.0.0-beta03"
    const val junit = "4.12"
    const val sqlDelight = "1.2.1"
    const val ktor = "1.2.6"
    const val stately = "0.9.5"
    const val multiplatformSettings = "0.5"
    const val coroutines = "1.3.3-native-mt"
    const val koin = "3.0.0-alpha-8"
    const val serialization = "0.14.0"
    const val kotlinNativeCocoapods = "0.6"
    const val klock = "1.7.0"
    const val buildKonfig = "0.5.0"
}

object Deps {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidDradlePlugin}"
    const val material = "com.google.android.material:material:${Versions.androidX}"
    const val stately = "co.touchlab:stately:${Versions.stately}"
    const val kotlinNativeCocoapods = "co.touchlab:kotlinnativecocoapods:${Versions.kotlinNativeCocoapods}"
    const val klock = "com.soywiz.korlibs.klock:klock:${Versions.klock}"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val krypto = "com.soywiz.korlibs.krypto:krypto:1.10.0"
    const val buildKonfig = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:${Versions.buildKonfig}"
    const val junit = "junit:junit:${Versions.junit}"

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidX}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidX}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidX}"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
        const val jdk = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object SqlDelight {
        const val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:${Versions.sqlDelight}"
        const val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val driverIos = "com.squareup.sqldelight:ios-driver:${Versions.sqlDelight}"
    }

    object Ktor {
        const val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val androidCore = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
        const val iosCore = "io.ktor:ktor-client-core-native:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val commonJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val androidJson = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
        const val iosJson = "io.ktor:ktor-client-json-native:${Versions.ktor}"
        const val commonSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val androidSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
        const val iosSerialization = "io.ktor:ktor-client-serialization-native:${Versions.ktor}"
        const val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val androidLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
        const val iosLogging = "io.ktor:ktor-client-logging-native:${Versions.ktor}"
    }

    object Koin {
        const val core = "org.koin:koin-core:${Versions.koin}"
        const val android = "org.koin:koin-android:${Versions.koin}"
    }

    object MultiplatformSettings {
        const val core = "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
        const val test = "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}"
    }

    object AndroidXTest {
        const val core = "androidx.test:core:${Versions.androidX}"
        const val junit = "androidx.test.ext:junit:${Versions.androidX}"
        const val runner = "androidx.test:runner:${Versions.androidX}"
        const val rules = "androidx.test:rules:${Versions.androidX}"
        const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
    }

    object KotlinTest {
        const val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        const val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        const val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }

    val coroutinesExcludeNative: ExternalModuleDependency.() -> Unit = {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-core-native")
    }
}
