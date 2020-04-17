buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Deps.androidGradlePlugin)
        classpath(kotlin("gradle-plugin", Versions.kotlin))
        classpath(kotlin("serialization", Versions.kotlin))
        classpath(Deps.SqlDelight.gradle)
        classpath(Deps.kotlinNativeCocoapods)
        classpath(Deps.buildKonfig)
        classpath(Deps.Navigation.safeArgsGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://dl.bintray.com/touchlabpublic/kotlin")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
