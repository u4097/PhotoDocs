plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("androidx.navigation.safeargs")
    id("org.jmailen.kotlinter") version "1.21.0"
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.oleg.photodocs"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isUseProguard = false // user R8 instead
        }
    }

    compileOptions {
        setSourceCompatibility(1.8)
        setTargetCompatibility(1.8)
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("debug").java.srcDirs("src/debug/kotlin")
        getByName("release").java.srcDirs("src/release/kotlin")

    }

    androidExtensions {
        isExperimental = true
    }

}


dependencies {

    /** Kotlin */
    implementation(Libraries.kotlin)

    implementation(Libraries.androidx)
    implementation(Libraries.ktx)
    implementation(Libraries.lifecycle)

    /** Constraint Layout */
    implementation(Libraries.constraintLayout)


    /** Navigation */
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUI)


    /** Networkin */
    // Coroutines
    implementation(Libraries.coroutines)
    // Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitCoroutines)
    // OkHttp
    implementation(Libraries.okhttp)
    implementation(Libraries.okhttpLoging)
    // Moshi
    implementation(Libraries.moshi)


    /** Design */
    // Fresco
    implementation(Libraries.fresco)

    /** Debug Drawer */
    implementation(Libraries.debugDrawer)
    implementation(Libraries.debugDrawerLeak)
    implementation(Libraries.debugDrawerRetrofit)
    implementation(Libraries.debugDrawerOkHTTP)
    implementation(Libraries.debugDrawerTimber)

    /** Logs */
    // Timber
    implementation(Libraries.timber)
    // Chuck
    debugImplementation(Libraries.chuck)
    releaseImplementation(Libraries.chuckRelease)

    /** LeakCanary */
    debugImplementation (Libraries.leakCanaryAndroid)
    releaseImplementation (Libraries.leakCanaryAndroidNoOp)

    // material dialog
    implementation(Libraries.materialDialog)

    testImplementation ("junit:junit:4.12")
    testImplementation("androidx.test.ext:junit:1.1.0")
    testImplementation("androidx.test.espresso:espresso-core:3.1.1")
}
