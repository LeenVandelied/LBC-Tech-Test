buildscript {
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin.serialization)
    }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.google.devtools.ksp) apply false
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "17"
    }

    project.pluginManager.apply(ModuleProjectPlugin::class)
}

open class ModuleProjectPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.all {
            when (this) {
                is com.android.build.gradle.AppPlugin ->
                    project.extensions.getByType<com.android.build.gradle.AppExtension>().apply {
                        applyConfig()
                    }
                is com.android.build.gradle.LibraryPlugin ->
                    project.extensions
                        .getByType<com.android.build.gradle.LibraryExtension>()
                        .apply { applyConfig() }
            }
        }
    }

    private fun com.android.build.gradle.BaseExtension.applyConfig() {
        compileSdkVersion(AdevintaConfig.Android.compileSdkVersion)

        defaultConfig.apply {
            minSdk = AdevintaConfig.Android.minSdkVersion
            targetSdk = AdevintaConfig.Android.targetSdkVersion

            vectorDrawables.useSupportLibrary = true
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            buildConfigField("int", "VERSION_CODE", "${AdevintaConfig.Versions.code}")
            buildConfigField("String", "VERSION_NAME", "\"${AdevintaConfig.Versions.name}\"")

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}
