package com.t.imglistdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.t.imglistdemo.R;
import com.t.imglistdemo.common.Constant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    private WebView webView;

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        addView();
    }

    private void initView() {
        frameLayout = findViewById(R.id.container);

        String url = getIntent().getStringExtra(Constant.COMMON_ACTIVITY_EXTRA_KEY);
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.loadUrl(url);
    }

    private void addView() {
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        webView.setLayoutParams(lineParams);
        frameLayout.addView(webView);
    }

    @Override
    protected void onDestroy() {
        removeWebView();
        super.onDestroy();
    }

    private void removeWebView() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
            webView.setVisibility(View.GONE);
        }
    }
}
