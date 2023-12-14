@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.adevinta.home"
    android.buildFeatures.buildConfig = true

    buildFeatures { compose = true }

    composeOptions { kotlinCompilerExtensionVersion = "1.5.1" }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":design-system"))
    implementation(libs.core.ktx)
    implementation(platform(libs.bom.compose))
    implementation(libs.compose.ui)
    implementation(libs.compose.nav)
    implementation(libs.material3)
    implementation(libs.glide.compose)
    implementation(libs.koin.compose)
    implementation(libs.koin.android)
    implementation(libs.compose.tooling.preview)
    implementation(libs.material.pullrefresh)
    implementation(libs.kotlinx.coroutines .test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.junit)
}
