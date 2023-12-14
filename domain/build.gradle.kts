@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.adevinta.domain"
    android.buildFeatures.buildConfig = true
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(libs.core.ktx)
    implementation(libs.koin.android)
    testImplementation(libs.mockito.core)
    testImplementation(libs.junit)
}
