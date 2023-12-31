plugins { `kotlin-dsl` }

repositories {
    google()
    mavenCentral()
}

// We cannot use libs here :(
dependencies {
    compileOnly("com.android.tools.build:gradle:8.0.2")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    implementation(gradleApi())
    implementation(localGroovy())
}
