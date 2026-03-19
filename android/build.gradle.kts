plugins {
    id("com.android.application") version "8.2.0"
    kotlin("android") version "1.9.20"
}

android {
    namespace = "com.adventureisland.game"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.adventureisland.game"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(libs.libgdx.backend.android)
    implementation(libs.libgdx.platform)
    coreLibraryDesugaring(libs.core.library.desugaring)
}
