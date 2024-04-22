plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.srcorp.imagecachingproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.srcorp.imagecachingproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //retrofit
   // implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation(libs.com.retrofit)
    implementation("com.squareup.retrofit2:converter-scalars:2.6.2")
    //gson convertor
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //live data with mvvm
    val lifecycle_version = "2.7.0"

    // ViewModel
    implementation( "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation( "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation( "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    // Saved state module for ViewModel
    implementation( "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation( "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
    //coroutine
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}