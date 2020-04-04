import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
}

android {
    compileSdkVersion(Versions.compileSdk)
    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Versions.versionCode
        versionName = Versions.versionName
    }
    buildFeatures {
        buildConfig = false
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
                api(project(":shared:data"))
                implementation(kotlin("stdlib-common", Versions.kotlin))
                implementation(Deps.Coroutines.common)
                implementation(Deps.Koin.core)
            }
        }
        androidMain {
            dependencies {
                implementation(kotlin("stdlib", Versions.kotlin))
                implementation(Deps.Coroutines.jdk)
                implementation(Deps.Coroutines.android)
            }
        }
        iosMain {
            dependencies {
                implementation(Deps.Coroutines.native)
            }
        }
    }
}


