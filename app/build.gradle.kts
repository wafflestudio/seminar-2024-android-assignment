plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization")  // serialization 플러그인 활성화
    id("androidx.navigation.safeargs")  // Safe Args 플러그인 추가
}

android {
    namespace = "com.wafflestudio.waffleseminar2024"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wafflestudio.waffleseminar2024"
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Jetpack Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // ViewPager2
    implementation(libs.androidx.viewpager2)

    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.kotlinx.serialization.json)  // Kotlinx Serialization JSON 의존성 추가
    implementation("io.coil-kt:coil:2.2.2") // Coil 기본 버전
    implementation("io.coil-kt:coil-svg:2.2.2") // SVG 이미지 지원 (필요시 추가)
    implementation("com.google.code.gson:gson:2.9.0")  // Gson 의존성 추가

}
