#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_yogadarma_core_utils_Keys_baseUrl(JNIEnv *env, jobject object) {
    std::string baseUrl = "https://api.themoviedb.org/3/movie/";
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C" JNIEXPORT jstring

JNICALL
Java_com_yogadarma_core_utils_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string apiKey = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjY4MTQwMTE0M2M3MzZjODU1MzA2OWUwMjkzNDk3MyIsInN1YiI6IjVmYTNhNmE4OGM3YjBmMDAzZjczZDc5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mcbYqSFqYaj3omnZBT7UKLK_iirnCwS-qHGVBqmWkO4";
    return env->NewStringUTF(apiKey.c_str());
}
