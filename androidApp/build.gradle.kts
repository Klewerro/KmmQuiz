plugins {
    id("kotlin-kapt")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hiltGradlePlugin)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.klewerro.kmmquiz.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.klewerro.kmmquiz.android"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    ktlint {
        filter {
            exclude { it.file.path.contains(layout.buildDirectory.dir("generated").get().toString()) }
        }
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.hilt)
    kapt(libs.bundles.hiltKapt)
    implementation(libs.ktor.client.android)
    implementation(libs.kotlin.dateTime)

    debugImplementation(libs.compose.ui.tooling)
}
