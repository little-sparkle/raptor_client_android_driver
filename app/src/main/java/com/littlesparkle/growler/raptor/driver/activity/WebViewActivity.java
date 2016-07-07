package com.littlesparkle.growler.raptor.driver.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.littlesparkle.growler.raptor.driver.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView mWebView;

    @BindView(R.id.number_progress_bar)
    NumberProgressBar mNpb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ButterKnife.bind(this);

        mNpb.setMax(100);
        mNpb.setProgress(0);

        initWebView();

        String url = getIntent().getStringExtra("url");
        mWebView.loadUrl(url);
    }

    private void initWebView() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mNpb.setProgress(newProgress);
                if (newProgress == 100) {
                    mNpb.setVisibility(View.INVISIBLE);
                } else {
                    mNpb.setVisibility(View.VISIBLE);
                }
            }
        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        mWebView.requestFocusFromTouch();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {

            super.onBackPressed();
        }
    }
}
