@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.adevinta.design.system"
    android.buildFeatures.buildConfig = true

    buildFeatures { compose = true }

    composeOptions { kotlinCompilerExtensionVersion = "1.5.1" }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(platform(libs.bom.compose))
    implementation(libs.compose.ui)
    implementation(libs.compose.lifecycle.view)
    implementation(libs.material3)
    implementation(libs.material3.window.size)
    implementation(libs.compose.icons.core)
    implementation(libs.compose.tooling.preview)
}
