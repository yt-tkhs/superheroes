plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":shared:core"))

    implementation(kotlin("stdlib-jdk8", Versions.kotlin))

    implementation(Deps.Coroutines.jdk)
    implementation(Deps.Coroutines.android)

    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(Deps.AndroidX.recyclerView)
    implementation(Deps.material)

    implementation(Deps.Lifecycle.viewModelKtx)
    implementation(Deps.Lifecycle.liveDataKtx)
    implementation(Deps.Lifecycle.savedState)
    kapt(Deps.Lifecycle.compiler)
    implementation(Deps.Lifecycle.commonJava8)

    implementation(Deps.Koin.android)
    implementation(Deps.Koin.androidViewModel)

    implementation(Deps.Navigation.fragmentKtx)
    implementation(Deps.Navigation.uiKtx)

    implementation(Deps.Insetter.core)
    implementation(Deps.Insetter.ktx)

    implementation(Deps.klock)

    implementation(Deps.groupie)
    implementation(Deps.coil)

    implementation(Deps.timber)

    testImplementation(Deps.junit)
    testImplementation(Deps.Navigation.testing)
    androidTestImplementation(Deps.AndroidXTest.junit)
    androidTestImplementation(Deps.AndroidXTest.espresso)
}
