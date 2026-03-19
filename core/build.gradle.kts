plugins {
    alias(libs.plugins.kotlin.library)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    api(libs.libgdx.core)
}
