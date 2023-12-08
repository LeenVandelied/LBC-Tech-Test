plugins {
    id("com.android.library")
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("plugin.serialization")
}

android {
    compileSdk = AdevintaConfig.Android.compileSdkVersion
    namespace = "com.adevinta.core"

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
    implementation(libs.kotlin.serialization)

    implementation(libs.koin.android)
}