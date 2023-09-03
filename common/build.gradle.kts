plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yogadarma.common"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w500\"")
        buildConfigField("String", "DATE_FORMAT_1", "\"yyyy-MM-dd\"")
        buildConfigField("String", "DATE_FORMAT_2", "\"dd MMMM yyyy\"")
        buildConfigField("String", "DATE_FORMAT_3", "\"yyyy-MM-dd'T'HH:mm:ss.SSSZ\"")
        buildConfigField("String", "DATE_FORMAT_4", "\"dd MMMM yyyy HH:mm\"")
        buildConfigField("String", "MOVIE_ID", "\"MOVIE_ID\"")
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.glide)
    implementation(Dependencies.jodaTime)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.testJunit)
    androidTestImplementation(Dependencies.espressoCore)
}