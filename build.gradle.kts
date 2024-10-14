plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false}

buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")  // 안드로이드 빌드 툴
        classpath("com.google.gms:google-services:4.3.15")  // Google Services 플러그인
    }}

//allprojects {
 //   repositories {
//        google()
 //       mavenCentral()
 //  }
//}
