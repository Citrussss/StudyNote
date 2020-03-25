//
// Created by 29283 on 2020/3/25.
//

#include "PornHub.h"
#include <jni.h>

extern "C" jint
Java_com_sure_app_TestSoHelper_intFromJNI(JNIEnv *env, jobject thiz, jint a, jint b) {
    return a + b;
}