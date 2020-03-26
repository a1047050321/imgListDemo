package com.t.imglistdemo.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;
import com.t.imglistdemo.adapter.ImgListHolder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImageLoader extends AsyncTask<String, Integer, Bitmap> {

    private static final String TAG = "ImageLoader";

    private Context ctx;
    private Bitmap bitmap = null;
    private InputStream is = null;
    private String url = null;
    private ImgListHolder holder = null;
    private DiskLruCache.Snapshot snapshot = null;
    private FileInputStream fileInputStream = null;
    private FileDescriptor fileDescriptor = null;
    private BufferedInputStream in = null;
    private BufferedOutputStream out = null;

    public ImageLoader(Context ctx, ImgListHolder holder) {
        this.ctx = ctx;
        this.holder = holder;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        Bitmap cacheBitmap = getBitmapCache(url);

        if (cacheBitmap != null) {
            Log.d(TAG, "缓存找到 return");
            return cacheBitmap;
        } else {
            try {
                snapshot = LruCacheUtil.getInstance(ctx).getDiskLruCache().get(getDiskKey(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (snapshot == null) {
                Log.d(TAG, "Disk = null");
                URL imgUrl = null;
                try {
                    imgUrl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    is = conn.getInputStream();
                    in = new BufferedInputStream(is, 8 * 1024);
                    DiskLruCache.Editor editor = LruCacheUtil.getInstance(ctx)
                            .getDiskLruCache().edit(getDiskKey(url));

                    OutputStream outputStream = editor.newOutputStream(0);
                    out = new BufferedOutputStream(outputStream, 8 * 1024);
                    int b;
                    while ((b = in.read()) != -1) {
                        out.write(b);
                    }
                    editor.commit();
                    LruCacheUtil.getInstance(ctx).getDiskLruCache().flush();
                    conn.disconnect();
                    out.close();
                    in.close();

                    snapshot = LruCacheUtil.getInstance(ctx).getDiskLruCache().get(getDiskKey(url));
                    fileDescriptor = fileInputStream.getFD();
                    fileInputStream = (FileInputStream) snapshot.getInputStream(0);

                    if (fileDescriptor != null) {
                        Bitmap diskLruBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        if (diskLruBitmap == null) {
                            return null;
                        } else {
                            putBitmap2Cache(url, diskLruBitmap);
                            return diskLruBitmap;
                        }
                    }
                    return bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imageView = holder.getIv();
        if (imageView.getTag().equals(url)) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        }
        super.onPostExecute(bitmap);
    }

    private void putBitmap2Cache(String url, Bitmap diskLruBitmap) {
        if (getBitmapCache(url) == null) {
            if (diskLruBitmap == null) {
                return;
            }else {
                LruCacheUtil.getInstance(ctx).getLruCache().put(url,diskLruBitmap);
            }
        }
    }

    private String getDiskKey(String url) {
        String cacheKey;
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            cacheKey = bytes2HexStr(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
            e.printStackTrace();
        }
        return cacheKey;
    }

    private String bytes2HexStr(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String hexStr = Integer.toHexString(0xff & digest[i]);
            if (hexStr.length() == 1) {
                sb.append("0");
            }
            sb.append(hexStr);
        }
        return sb.toString();
    }

    public Bitmap getBitmapCache(String key) {
        return (Bitmap) LruCacheUtil.getInstance(ctx).getLruCache().get(key);
    }
}
