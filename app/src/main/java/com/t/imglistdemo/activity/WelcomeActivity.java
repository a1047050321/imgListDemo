package com.t.imglistdemo.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.t.imglistdemo.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;

public class WelcomeActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = "WelcomeActivity";

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int REQUEST_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getPremission();
    }

    private void getPremission() {
        if (EasyPermissions.hasPermissions(this, PERMISSIONS_STORAGE)) {
            startMainActivity();
        } else {
            EasyPermissions.requestPermissions(this, "",
                    REQUEST_PERMISSION_CODE, PERMISSIONS_STORAGE);
        }
    }

    private void startMainActivity() {
        Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(mainIntent);
        WelcomeActivity.this.finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG,"granted");
        startMainActivity();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        startMainActivity();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
