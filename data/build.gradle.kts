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
    implementation(libs.okhttp)
    implementation(libs.okhttp.curl)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.coroutine)
    implementation(libs.room)
    implementation(libs.room.runtime)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
    implementation(libs.koin.android)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit)
    testImplementation(libs.junit.api)
    testImplementation(libs.junit.engine)
    testImplementation(libs.kotlinx.coroutines.test)
}

tasks.withType<Test> { useJUnitPlatform() }
