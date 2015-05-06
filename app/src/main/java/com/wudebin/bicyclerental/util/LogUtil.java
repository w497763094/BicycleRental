package com.wudebin.bicyclerental.util;

import android.util.Log;

public class LogUtil {

    public static final boolean Debug = true;

    private LogUtil() {
        throw new UnsupportedOperationException("logUtil cannot be instantiated");
    }

    public static void i(String tag, String msg) {
        if (Debug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Debug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Debug) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (Debug) {
            Log.v(tag, msg);
        }
    }
}
