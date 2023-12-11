object AdevintaConfig {
    object Android {
        const val compileSdkVersion = 34
        const val minSdkVersion = 24
        const val targetSdkVersion = 34
    }

    object Versions {
        private const val major = 40
        private const val minor = 6
        private const val patch = 1
        private const val recette = 0

        const val code: Int = major * 10000 + minor * 1000 + patch * 100 + recette
        const val name = "$major.$minor.$patch-$recette"
    }
}