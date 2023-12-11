@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.devtools.ksp)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.adevinta.data"
    android.buildFeatures.buildConfig = true
}

dependencies {
    implementation(project(":core"))
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(platform(libs.bom.compose))
    implementation(libs.compose.ui)
    implementation(libs.compose.nav)
    implementation(libs.kotlin.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.curl)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.coroutine)
    implementation(libs.gson)
    implementation(libs.paperdb)
    implementation(libs.room)
    ksp(libs.room.compiler)

    implementation(libs.koin.android)
}