package com.littlesparkle.growler.raptor.driver.activity;

import android.content.Intent;

import com.littlesparkle.growler.library.activity.BaseLoginActivity;
import com.littlesparkle.growler.library.dialog.DialogHelper;
import com.littlesparkle.growler.library.http.BaseHttpSubscriber;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.misc.MiscHelper;
import com.littlesparkle.growler.library.user.UserRequest;
import com.littlesparkle.growler.library.user.UserSignInResponse;

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
        String phoneNumber = mMobileInput.getText().toString();
        if ("".equals(phoneNumber)) {
            return;
        }
        if (!MiscHelper.checkPhoneNumber(phoneNumber)) {
            return;
        }

        String pwd = mPwdInput.getText().toString();
        if ("".equals(pwd)) {
            return;
        }
        if (!MiscHelper.checkPassword(pwd)) {
            return;
        }

        new UserRequest().signin(new BaseHttpSubscriber<UserSignInResponse>(this, this) {
            @Override
            protected void onError(String errorMessage) {
                super.onError(errorMessage);
                Logger.e("driver signin error: " + errorMessage);
            }

            @Override
            public void onNext(UserSignInResponse driverSignInResponse) {
                Logger.log("driver signin : " + driverSignInResponse);
                if (driverSignInResponse.data.user.is_driver == 1 &&
                        driverSignInResponse.data.user.verified == 1) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    if (driverSignInResponse.data.user.is_driver == 0) {
                        // todo: start to register
                        Intent it = new Intent(LoginActivity.this, ApplyDriverActivity.class);
                        it.putExtra("title", "申请司机");
                        it.putExtra("url", "http://www.baidu.com");
                        startActivity(it);
                    } else if (driverSignInResponse.data.user.verified == 0) {
                        // todo: toast a tip: is under verifying
                    } else {

                    }
                }
            }
        }, phoneNumber, pwd);
    }
}
