package com.littlesparkle.growler.raptor.driver.activity;

import android.content.Intent;

import com.littlesparkle.growler.library.activity.BaseLoginActivity;
import com.littlesparkle.growler.library.dialog.DialogHelper;

public class LoginActivity extends BaseLoginActivity {
    @Override
    protected void onRegisterClick() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    @Override
    protected void onForgetPasswordClick() {
        DialogHelper.showDialogWithMessage(this, "forget password");
    }

    @Override
    protected void onLoginClick() {
        DialogHelper.showDialogWithMessage(this, "login");
    }
}
