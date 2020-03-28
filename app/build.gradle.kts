plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Versions.compileSdk)

    defaultConfig {
        applicationId = "app.ytak.superheroes"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":core"))

    implementation(kotlin("stdlib-jdk8", Versions.kotlin))

    implementation(Deps.Coroutines.jdk)
    implementation(Deps.Coroutines.android)

    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(Deps.material)

    implementation(Deps.Koin.android)

    implementation(Deps.timber)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.AndroidXTest.junit)
    androidTestImplementation(Deps.AndroidXTest.espresso)
}
