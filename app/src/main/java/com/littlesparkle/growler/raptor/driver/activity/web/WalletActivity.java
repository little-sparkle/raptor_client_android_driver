package com.littlesparkle.growler.raptor.driver.activity.web;

import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.webview.WebViewActivity;


public class WalletActivity extends WebViewActivity {

    @Override
    public void onJsSuccess(String result) {
        Logger.log("WalletActivity: onSuccess -- " + result);
    }

    @Override
    public void onJsFailed(String result) {
        Logger.log("WalletActivity: onFailed -- " + result);
    }
}
