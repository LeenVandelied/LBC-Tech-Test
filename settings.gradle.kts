pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.google.com")
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.fabric.io/public")
    }
}

rootProject.name = "LBCTechTest"

include(":app")

include(":data")

include(":core")

include(":home")

include(":design-system")

include(":design-system:core")

include(":domain")
