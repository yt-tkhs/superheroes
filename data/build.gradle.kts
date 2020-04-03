import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
    id("com.codingfeline.buildkonfig")
}

android {
    compileSdkVersion(Versions.compileSdk)
    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    android()

    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }
    targets.getByName<KotlinNativeTarget>("ios").compilations["main"].run {
        kotlinOptions.freeCompilerArgs += listOf("-Xobjc-generics", "-Xg0")
    }
    version = "0.1"

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common", Versions.kotlin))
                implementation(Deps.Coroutines.common)
                implementation(Deps.Ktor.commonCore)
                implementation(Deps.Ktor.commonJson)
                implementation(Deps.Ktor.commonSerialization)
                implementation(Deps.Ktor.commonLogging)
                implementation(Deps.SqlDelight.runtime)
                implementation(Deps.stately)
                implementation(Deps.MultiplatformSettings.core)
                implementation(Deps.Koin.core)
                implementation(Deps.klock)
                implementation(Deps.krypto)
            }
        }
        commonTest {
            dependencies {
                implementation(Deps.KotlinTest.common)
                implementation(Deps.KotlinTest.annotations)
                implementation(Deps.Coroutines.jdk)
                implementation(Deps.Coroutines.common)
                implementation(Deps.Coroutines.test)
                implementation(Deps.SqlDelight.runtime)
                implementation(Deps.MultiplatformSettings.test)
            }
        }
        androidMain {
            dependencies {
                implementation(kotlin("stdlib", Versions.kotlin))
                implementation(Deps.Coroutines.jdk)
                implementation(Deps.Coroutines.android)
                implementation(Deps.Ktor.androidCore)
                implementation(Deps.Ktor.androidJson)
                implementation(Deps.Ktor.android)
                implementation(Deps.Ktor.androidSerialization)
                implementation(Deps.Ktor.androidLogging)
                implementation(Deps.SqlDelight.driverAndroid)
                implementation(Deps.timber)
            }
        }
        iosMain {
            dependencies {
                implementation(Deps.Coroutines.native)
                implementation(Deps.Ktor.ios, Deps.coroutinesExcludeNative)
                implementation(Deps.Ktor.iosCore, Deps.coroutinesExcludeNative)
                implementation(Deps.Ktor.iosJson, Deps.coroutinesExcludeNative)
                implementation(Deps.Ktor.iosSerialization)
                implementation(Deps.Ktor.iosLogging)
                implementation(Deps.SqlDelight.driverIos)
            }
        }
    }
}

sqldelight {
    database("SuperheroesDb") {
        packageName = "app.ytak.superheroes.db"
    }
}

buildkonfig {
    packageName = "app.ytak.superheroes.data"

    defaultConfigs {
        val localProperties = gradleLocalProperties(rootDir)
        buildConfigField(Type.STRING, "MARVEL_PUBLIC_KEY", localProperties.getProperty("marvel.publicKey"))
        buildConfigField(Type.STRING, "MARVEL_PRIVATE_KEY", localProperties.getProperty("marvel.privateKey"))
    }
}

val NamedDomainObjectContainer<KotlinSourceSet>.androidMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named<KotlinSourceSet>("androidMain")

val NamedDomainObjectContainer<KotlinSourceSet>.iosMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named<KotlinSourceSet>("iosMain")


