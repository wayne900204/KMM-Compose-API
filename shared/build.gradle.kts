import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // https://ktor.io/docs/getting-started-ktor-client.html#add-dependencies
                // 需要裝上 Client 的官方 client 套件  這個類似 flutter 的 https 套件
                implementation("io.ktor:ktor-client-core:1.4.1")
                // https://ktor.io/docs/json-feature.html#install_feature
                // 去支援 serialization 套件
                implementation("io.ktor:ktor-client-serialization:1.4.1")
                // https://github.com/Kotlin/kotlinx.coroutines
                // 多工、異步 的套件 例如：launch
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
                // https://kotlinlang.org/docs/serialization.html
                // They also provide kotlinx-serialization-core artifact that contains all serialization API but does not have bundled serialization format with it
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.1.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.3.0")
                implementation("io.ktor:ktor-client-android:1.4.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.4.1")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
    }
    configurations {
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}