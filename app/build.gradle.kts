plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.mediahive"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mediahive"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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

    //NavCompose
    val nav_version = "2.9.0"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //ComposeFoundation
    implementation("androidx.compose.foundation:foundation:1.8.1")

    //Animación
    implementation("com.google.accompanist:accompanist-navigation-animation:0.34.0")
    implementation ("androidx.compose.animation:animation:1.6.0")
    implementation ("androidx.compose.animation:animation-graphics:1.6.0")

    implementation ("androidx.compose.material3:material3:1.2.0")  // o versión más reciente
    implementation ("androidx.compose.ui:ui:1.6.0")

    implementation ("androidx.compose.material:material-icons-core:1.6.0")  // Para íconos base
    implementation ("androidx.compose.material:material-icons-extended:1.6.0")  // Íconos adicionales

    // Para cargar imágenes desde URL (Coil)
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Para manejo de clics
    implementation ("androidx.compose.foundation:foundation:1.6.0")


}