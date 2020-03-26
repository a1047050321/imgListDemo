package com.t.imglistdemo.util;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;
import com.t.imglistdemo.common.Constant;

import java.io.File;
import java.io.IOException;


public class LruCacheUtil {
    private static LruCacheUtil instance;

    private LruCache lruCache;
    private DiskLruCache diskLruCache;

    private Context ctx;

    public static LruCacheUtil getInstance(Context ctx) {
        if (instance == null) {
            synchronized (LruCacheUtil.class) {
                if (instance == null) {
                    instance = new LruCacheUtil(ctx);
                }
            }
        }
        return instance;
    }

    private LruCacheUtil(Context ctx) {
        this.ctx = ctx;
        int sizeOfLru =  (int) ((Runtime.getRuntime().maxMemory() / 1024) / 8);
        init(sizeOfLru);
    }

    private void init(int sizeOfLru) {
        lruCache = new LruCache<String, Bitmap>(sizeOfLru) {

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
        File photo = getDiskCacheDir(ctx, Constant.PHOTO_PATH);
        if (!photo.exists()) {
            photo.mkdirs();
        }
        try {
            diskLruCache = DiskLruCache.open(photo, getAppVersion(ctx), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public File getDiskCacheDir(Context ctx, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = ctx.getExternalCacheDir().getPath();
        } else {
            cachePath = ctx.getCacheDir().getPath();
        }
        return new File(String.format("%s%s%s", cachePath, File.separator, uniqueName));
    }

    public int getAppVersion(Context ctx) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public LruCache getLruCache() {
        return lruCache;
    }

    public DiskLruCache getDiskLruCache() {
        return diskLruCache;
    }
}
