plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
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
    }

    androidExtensions {
        isExperimental = true
    }


}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.21")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.1")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")


    testImplementation ("junit:junit:4.12")
    testImplementation("androidx.test.ext:junit:1.1.0")
    testImplementation("androidx.test.espresso:espresso-core:3.1.1")
}
