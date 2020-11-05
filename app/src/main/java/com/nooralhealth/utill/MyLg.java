package com.nooralhealth.utill;


import android.util.Log;

public class MyLg {

    private static boolean DEBUG = true;

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (DEBUG) {
            Log.w(tag, message);
        }
    }

    //With Error
    public static void d(String tag, String message, Throwable e) {
        d(tag, message);
        printLogStackTrace(e);
    }

    public static void e(String tag, String message, Throwable e) {
        e(tag, message);
        printLogStackTrace(e);
    }

    public static void i(String tag, String message, Throwable e) {
        i(tag, message);
        printLogStackTrace(e);
    }

    public static void v(String tag, String message, Throwable e) {
        v(tag, message);
        printLogStackTrace(e);
    }

    public static void w(String tag, String message, Throwable e) {
        w(tag, message);
        printLogStackTrace(e);
    }

    public static void printStackTrace(Throwable e) {
        if (DEBUG) {
            e.printStackTrace();
        }
    }

    public static void printLogStackTrace(Throwable e) {
        if (DEBUG) {
            e.printStackTrace();
        }
    }
}
