@file:Suppress("UNUSED_EXPRESSION", "DEPRECATION")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.app.dailyjounral"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
    }


    defaultConfig {
        applicationId = "com.app.dailyjounral"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled ; true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    dataBinding.enable = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    lintOptions {
        isCheckReleaseBuilds = false
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.databinding.runtime)



    //Image Process

    implementation (libs.glide)
    implementation (libs.ssp.android)
    implementation(libs.firebase.auth)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    //Networking
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)
    implementation (libs.adapter.rxjava2)

    //RX JAVA
    implementation (libs.rxandroid)
    implementation (libs.rxbinding)

    implementation (libs.dexter)

    implementation ("com.google.android.gms:play-services-auth:21.2.0")

    implementation ("androidx.multidex:multidex:2.0.1")

    // download sdk
 //   implementation ("com.facebook.android:facebook-android-sdk:4.17.0")

}