plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(Versions.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(kotlin("stdlib"))

    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.appCompat)

    implementation(Deps.Lifecycle.liveDataKtx)
    implementation(Deps.Lifecycle.commonJava8)

    implementation(Deps.Koin.android)
    implementation(Deps.Koin.androidViewModel)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.AndroidXTest.junit)
    androidTestImplementation(Deps.AndroidXTest.espresso)
}
