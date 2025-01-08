import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.devtools.ksp")

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

val apiKeyPropertiesFile = rootProject.file("apiKey.properties")
val apiKeyProperties = Properties()

apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))

android {
    namespace = "com.example.movietmdbapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.movietmdbapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"${apiKeyProperties["API_KEY"]}\"")
        buildConfigField("String", "BASE_URL", "\"${apiKeyProperties["BASE_URL"]}\"")
        buildConfigField("String", "BASE_URL_IMAGE", "\"${apiKeyProperties["BASE_URL_IMAGE"]}\"")

        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.compose.material:material-icons-extended:1.7.5")
    implementation("com.google.accompanist:accompanist-flowlayout:0.36.0")
    implementation("androidx.navigation:navigation-compose:2.8.4")

    // Coil
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Paging3
    val pagingVersion = "3.3.4"
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation("androidx.paging:paging-compose:$pagingVersion")

    // okHttp3
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Retrofit
    val retrofitVersion = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // DI - Hilt
    val hiltVersion = "2.51.1"
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // Room
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
}

kapt {
    correctErrorTypes = true
}