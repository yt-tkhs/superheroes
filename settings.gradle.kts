// Android
include(":android:app")
include(":android:core")
include(":android:common:extfun")
include(":android:common:widget")
include(":android:features:comics")

// Shared
include(":shared:core", ":shared:data")

rootProject.name = "Superheroes"
enableFeaturePreview("GRADLE_METADATA")
