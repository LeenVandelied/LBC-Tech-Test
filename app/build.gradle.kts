plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = AdevintaConfig.Android.compileSdkVersion
    namespace = "com.example.lbctechtest"

    defaultConfig {
        applicationId = "com.example.lbctechtest"
        minSdk = AdevintaConfig.Android.minSdkVersion
        targetSdk = AdevintaConfig.Android.targetSdkVersion
        versionCode = AdevintaConfig.Versions.code
        versionName = AdevintaConfig.Versions.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

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
}