package com.littlesparkle.growler.raptor.driver.webview;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class BaseWebChromeClient extends WebChromeClient {
    private OnOpenFileChooserListener mOpenFileChooserListener = null;

    public void setOnOpenFileChooserListener(OnOpenFileChooserListener openFileChooserListener) {
        mOpenFileChooserListener = openFileChooserListener;
    }

    // Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        if (mOpenFileChooserListener != null) {
            mOpenFileChooserListener.openFileChooserWithOneFile(uploadMsg);
        }
    }

    // Android 4.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    // Android 5.0+
    @Override
    @SuppressLint("NewApi")
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if (mOpenFileChooserListener != null) {
            mOpenFileChooserListener.openFileChooserWithMultiFiles(filePathCallback);
        }
        return true;
    }

    public interface OnOpenFileChooserListener {
        void openFileChooserWithOneFile(ValueCallback<Uri> uploadMsg);

        void openFileChooserWithMultiFiles(ValueCallback<Uri[]> filePathCallback);
    }
}
