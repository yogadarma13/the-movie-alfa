plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.yogadarma.core"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
        }
    }
}

dependencies {

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGson)
    implementation(Dependencies.loggingInterceptor)
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.daggerHiltCompiler)
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.turbine)
    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.archCoreTesting)
    testImplementation(Dependencies.coroutinesTest)
    androidTestImplementation(Dependencies.testJunit)
    androidTestImplementation(Dependencies.espressoCore)
}

kapt {
    correctErrorTypes = true
}