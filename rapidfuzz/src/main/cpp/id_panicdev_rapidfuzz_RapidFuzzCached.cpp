// SPDX-License-Identifier: Apache-2.0

#include<string>

#include <jni.h>
#include <rapidfuzz/fuzz.hpp>

#include "id_panicdev_rapidfuzz_RapidFuzzCached.h"

using namespace rapidfuzz;

extern "C" JNIEXPORT jlong JNICALL Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, nullptr);
    auto *scorer = (fuzz::CachedRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedRatio<std::string> *) ptr;
    delete scorer;
}

extern "C" JNIEXPORT jlong JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedPartialRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedPartialRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetPartialRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, nullptr);
    auto *scorer = (fuzz::CachedPartialRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedPartialRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedPartialRatio<std::string> *) ptr;
    delete scorer;
}

extern "C" JNIEXPORT jlong JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedTokenSortRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedTokenSortRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetTokenSortRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, nullptr);
    auto *scorer = (fuzz::CachedTokenSortRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedTokenSortRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedTokenSortRatio<std::string> *) ptr;
    delete scorer;
}

extern "C" JNIEXPORT jlong JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedPartialTokenSortRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedPartialTokenSortRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetPartialTokenSortRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, nullptr);
    auto *scorer = (fuzz::CachedPartialTokenSortRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedPartialTokenSortRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedPartialTokenSortRatio<std::string> *) ptr;
    delete scorer;
}

extern "C" JNIEXPORT jlong JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedTokenSetRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedTokenSetRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetTokenSetRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, nullptr);
    auto *scorer = (fuzz::CachedTokenSetRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedTokenSetRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedTokenSetRatio<std::string> *) ptr;
    delete scorer;
}

extern "C" JNIEXPORT jlong JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedPartialTokenSetRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedPartialTokenSetRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetPartialTokenSetRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, nullptr);
    auto *scorer = (fuzz::CachedPartialTokenSetRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedPartialTokenSetRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedPartialTokenSetRatio<std::string> *) ptr;
    delete scorer;
}

extern "C" JNIEXPORT jlong JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedTokenRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedTokenRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetTokenRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, NULL);
    auto *scorer = (fuzz::CachedTokenRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedTokenRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedTokenRatio<std::string> *) ptr;
    delete scorer;
}

extern "C" JNIEXPORT jlong JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedPartialTokenRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedPartialTokenRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetPartialTokenRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, nullptr);
    auto *scorer = (fuzz::CachedPartialTokenRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedPartialTokenRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedPartialTokenRatio<std::string> *) ptr;
    delete scorer;
}

extern "C" JNIEXPORT jlong JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedWeightedRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedWRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetWeightedRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, nullptr);
    auto *scorer = (fuzz::CachedWRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedWeightedRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedWRatio<std::string> *) ptr;
    delete scorer;
}

extern "C" JNIEXPORT jlong JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeNewCachedQuickRatio
        (JNIEnv *env, jclass clazz, jstring query) {
    const char *q = env->GetStringUTFChars(query, nullptr);
    auto *scorer = new fuzz::CachedQRatio(std::string(q));
    return (jlong) scorer;
}

extern "C" JNIEXPORT jdouble JNICALL Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeGetQuickRatio
        (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char *c = env->GetStringUTFChars(choice, nullptr);
    auto *scorer = (fuzz::CachedQRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

extern "C" JNIEXPORT void JNICALL
Java_id_panicdev_rapidfuzz_RapidFuzzCached_nativeFreeCachedQuickRatio
        (JNIEnv *env, jclass clazz, jlong ptr) {
    auto *scorer = (fuzz::CachedQRatio<std::string> *) ptr;
    delete scorer;
}
