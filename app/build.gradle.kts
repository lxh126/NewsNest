import org.gradle.internal.impldep.bsh.commands.dir
import java.util.regex.Pattern.compile


plugins {
    alias(libs.plugins.android.application)


}
android {
    buildFeatures {
        buildConfig= true
    }
}

android {
    namespace="com.app.luoxueheng"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.luoxueheng"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.cronet.embedded)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //数据解析
    implementation ("com.google.code.gson:gson:2.10.1")
    //图片加载
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //网络请求
    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.google.code.gson:gson:2.2.4")
    //implementation ("com.android.support:appcompat-v7:28.0.0")
    //implementation("org.projectlombok:lombok:1.18.6")
    //implementation("com.github.bumptech.glide:glide:4.11.0")
    //annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")
    //annotationProcessor("org.projectlombok:lombok:1.18.6")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    compile ("com.github.bumptech.glide:glide:3.7.0")
    implementation ("com.github.bumptech.glide:glide:4.x.x")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.x.x")
    implementation("com.fasterxml.jackson.core:jackson-core:2.11.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.11.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.1")
    implementation ("com.google.android.material:material:1.10.0")

    //compile fileTree(dir: 'libs', include: ['*.jar'])
   // compile ("com.android.support:appcompat-v7:22.2.0")
    //compile files('libs/volley.jar')
    // Retrofit
//    implementation ("com.squareup.retrofit2:retrofit2:2.9.0")
//    // OkHttp
//    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
//    // 用于Gson解析
//    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //implementation ("com.squareup.okhttp3:okhttp:4.9.0")// 请检查最新版本



}
buildscript {
    repositories {
        maven{ url =uri("https://maven.aliyun.com/nexus/content/groups/public/") }
//        jcenter()
        google()
        mavenCentral()
        jcenter{url =uri("https://jcenter.bintray.com/")}


    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.5.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

//allprojects {
//    repositories {
//        maven{ url =uri("https://maven.aliyun.com/nexus/content/groups/public/")}
////        jcenter()
//        maven { url =uri("https://jitpack.io") }
//
//        google()
//        mavenCentral()
//        //jcenter{url=uri ("https://jcenter.bintray.com/")}
//    }
//}
//task (Wrapper){
//    gradleVersion = '7.2'
//}
// 根项目的build.gradle
//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath 'com.android.tools.build:gradle:7.0.0'
//    }
//}

//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//    }
//}
//task (prepareKotlinBuildScriptModel) {
//
//}
tasks.register("prepareKotlinBuildScriptModel") {
}


