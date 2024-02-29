plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.hiltGradlePlugin) apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // To run ktLint in all kotlin submodules
}

buildscript {
    dependencies {
        classpath(libs.moko.resourcesGenerator)
    }
}
