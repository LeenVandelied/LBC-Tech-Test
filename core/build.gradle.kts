@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.adevinta.core"
    android.buildFeatures.buildConfig = true
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(platform(libs.bom.compose))
    implementation(libs.compose.ui)
    implementation(libs.compose.nav)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.koin.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}