package com.example.exercise01.util;

import android.util.Log;
import com.example.exercise01.BuildConfig;

public class LogUtils {

    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable throwable) {
        if (DEBUG) {
            Log.e(tag, message, throwable);
        }
    }
}
