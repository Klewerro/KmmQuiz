plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.hiltGradlePlugin) apply false
    alias(libs.plugins.sqlDelightPlugin).apply(false)
}

buildscript {
    dependencies {
        classpath(libs.moko.resourcesGenerator)
    }
}
