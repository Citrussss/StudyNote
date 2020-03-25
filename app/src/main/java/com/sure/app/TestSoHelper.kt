package com.sure.app

/**
 * author pisa
 * date  2020/3/25
 * version 1.0
 * effect :
 */
class TestSoHelper {
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public external fun intFromJNI(a: Int, b: Int): Int;

    companion object {
        init {
            System.loadLibrary("91-lib")
        }
    }
}