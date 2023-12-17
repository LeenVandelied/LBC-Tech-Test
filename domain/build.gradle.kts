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
    implementation(libs.room.paging)
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit)
    testImplementation(libs.junit.api)
    testImplementation(libs.kotlinx.coroutines.test)
    testRuntimeOnly(libs.junit.engine)
}

tasks.withType<Test> { useJUnitPlatform() }
