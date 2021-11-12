object Versions {
    var versionCode = 1
    val versionName = "1.0.0"
    val room_version = "2.3.0"
    val retrofit_version = "2.5.0"
    val okhttp3_version = "4.9.0"
    val rxAndroid = "2.1.1"
    val rxJava = "2.2.10"
    val glideVersion = "4.11.0"
    val daggerVersion = "2.23.2"
}

object Deps {
    // Room
    val room = "androidx.room:room-runtime:${Versions.room_version}"
    val roomRX = "androidx.room:room-rxjava2:${Versions.room_version}"
    val roomKTS = "androidx.room:room-ktx:${Versions.room_version}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    val roomTesting = "androidx.room:room-testing:${Versions.room_version}"

    // Dagger
    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.daggerVersion}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    val daggerAndroidProcessor =  "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"

    //Retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_version}"
    val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3_version}"
    val okhttp3LogginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3_version}"

    //RxJava
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"

    //Glide
    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
}