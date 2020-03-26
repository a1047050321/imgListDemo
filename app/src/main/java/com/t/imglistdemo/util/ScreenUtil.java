package com.t.imglistdemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ScreenUtil {

    private static ScreenUtil instance;

    private Context ctx;

    public static ScreenUtil getInstance(Context ctx) {
        if (instance == null) {
            synchronized (ScreenUtil.class) {
                if (instance == null) {
                    instance = new ScreenUtil(ctx);
                }
            }
        }
        return instance;
    }

    private ScreenUtil(Context ctx) {
        this.ctx = ctx;
    }

    public int getScreenWidth() {
        Resources resources = ctx.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int width = dm.widthPixels;
        return width;
    }


}
