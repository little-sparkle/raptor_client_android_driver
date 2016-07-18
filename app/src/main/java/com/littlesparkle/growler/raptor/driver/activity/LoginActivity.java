package com.littlesparkle.growler.raptor.driver.activity;

import com.littlesparkle.growler.library.activity.BaseLoginActivity;
import com.littlesparkle.growler.library.dialog.DialogHelper;

public class LoginActivity extends BaseLoginActivity {
    @Override
    protected void onForgetPasswordClick() {
        DialogHelper.showDialogWithMessage(this, "forget password");
    }

    @Override
    protected void onLoginClick() {
        DialogHelper.showDialogWithMessage(this, "login");
    }
}
