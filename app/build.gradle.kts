plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.androidkotlinbase"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.androidkotlinbase"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    flavorDimensions += "default"
    productFlavors {
        create("development") {
            buildConfigField("String", "BASE_URL", "\"https://api.jikan.moe/v4/\"")
            matchingFallbacks += listOf("development")
        }
        create("production") {
            buildConfigField("String", "BASE_URL", "\"https://api.jikan.moe/v4/\"")
            matchingFallbacks += listOf("production")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraint.layout)
    implementation(libs.androidx.navigation.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.swipe.refresh)
    implementation(libs.material)

    ksp(libs.room.kapt)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.koin)
    implementation(libs.koin.scope)
    implementation(libs.koin.viewmodel)
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("com.github.kittinunf.fuel:fuel-coroutines:2.3.1")
    implementation("com.github.kittinunf.fuel:fuel-moshi:2.3.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.logging.interceptor)
    implementation("io.coil-kt:coil:0.7.0")
    implementation(libs.sdp)
    implementation(libs.ssp)

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}