#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_yogadarma_core_utils_Keys_baseUrl(JNIEnv *env, jobject object) {
    std::string baseUrl = "https://api.goapi.id/v1/";
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C" JNIEXPORT jstring

JNICALL
Java_com_yogadarma_core_utils_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string apiKey = "GNkMpo3t4OZyFX8TomwU1RuYU4DJnF";
    return env->NewStringUTF(apiKey.c_str());
}
