//
// Created by Popov Bogdan on 27.07.17.
//

#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define DEBUG_TAG "NDK_native"

int theMaxOfTheTwo(int first, int second) {
    return first > second ? first : second;
}

void
Java_ru_popov_bodya_learningndk_MainActivity_helloLog(JNIEnv *env, jobject this, jstring logThis) {
    jboolean isCopy;
    const char *szLogThis = (*env)->GetStringUTFChars(env, logThis, &isCopy);

    __android_log_print(ANDROID_LOG_DEBUG, DEBUG_TAG, "NDK:LC: [%s]", szLogThis);

    (*env)->ReleaseStringUTFChars(env, logThis, szLogThis);
}


jstring
Java_ru_popov_bodya_learningndk_MainActivity_evaluateRandomNumberInText(JNIEnv *env, jobject this) {

    char *szFormat = "Random number is: %i";
    char *szResult;
    int n = 100;

    jlong sum = rand() % (n - 1) + 1;

    // malloc space for the resulting string
    szResult = malloc(sizeof(szFormat) + 20);

    // standard sprintf
    sprintf(szResult, szFormat, sum);

    // get an object string
    jstring result = (*env)->NewStringUTF(env, szResult);

    // cleanup
    free(szResult);
    return result;
}

jlong
Java_ru_popov_bodya_learningndk_MainActivity_evaluateRandomNumber(JNIEnv *env, jobject this) {
    int n = 100;
    jlong random = rand() % (n - 1) + 1;
    return random;
}

jint Java_ru_popov_bodya_learningndk_MainActivity_evaluateMaxValue(JNIEnv *env, jobject this,
                                                                   jint first, jint second) {
    return theMaxOfTheTwo(first, second);
}








