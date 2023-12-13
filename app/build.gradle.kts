@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    kotlin("android")
}

android {
    namespace = "com.adevinta.lbctechtest"
    android.buildFeatures.buildConfig = true

    defaultConfig {
        versionCode = AdevintaConfig.Versions.code
        versionName = AdevintaConfig.Versions.name
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures { compose = true }

    composeOptions { kotlinCompilerExtensionVersion = "1.5.1" }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":home"))
    implementation(project(":design-system"))

    implementation(libs.core.ktx)
    implementation(libs.kotlin.stdlib)

    implementation(libs.koin.android)

    implementation(platform(libs.bom.compose))
    implementation(libs.compose.ui)
    implementation(libs.compose.lifecycle.view)
    implementation(libs.compose.tooling)
    implementation(libs.material3)
    implementation(libs.compose.icons.core)
    implementation(libs.compose.icons.extended)
    implementation(libs.compose.nav)
    implementation(libs.material3.window.size)
    implementation(libs.core.splashscreen)
}
