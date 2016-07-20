package com.littlesparkle.growler.raptor.driver.activity;

import android.content.Intent;
import android.widget.Toast;

import com.littlesparkle.growler.library.activity.BaseLoginActivity;
import com.littlesparkle.growler.library.bean.User;
import com.littlesparkle.growler.library.dialog.DialogHelper;
import com.littlesparkle.growler.library.http.BaseHttpSubscriber;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.misc.MiscHelper;
import com.littlesparkle.growler.library.user.UserRequest;
import com.littlesparkle.growler.library.user.UserSignInResponse;
import com.littlesparkle.growler.raptor.driver.R;

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
        if ("".equals(phoneNumber) || !MiscHelper.checkPhoneNumber(phoneNumber)) {
            Toast.makeText(this, R.string.illegal_mobile, Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = mPwdInput.getText().toString();
        if ("".equals(pwd) || !MiscHelper.checkPassword(pwd)) {
            Toast.makeText(this, R.string.illegal_password, Toast.LENGTH_SHORT).show();
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
                User user = driverSignInResponse.data.user;
                user.persist(LoginActivity.this);

                Logger.log("driver signin : " + driverSignInResponse);
                if (user.is_driver == 1 && user.verified == 1) {
                    startMainActivity(user);
                } else {
                    if (user.is_driver == 0) {
                        startMainActivity(user);
                    } else if (user.verified == 0) {
                        Toast.makeText(LoginActivity.this, R.string.application_is_under_verifying, Toast.LENGTH_SHORT)
                                .show();
                    } else {

                    }
                }
            }
        }, phoneNumber, pwd);
    }

    private void startMainActivity(User user) {
        Intent it = new Intent(LoginActivity.this, MainActivity.class);
        it.putExtra("user", user);
        startActivity(it);
        finish();
    }

    private void startApplyDriverActivity(User user) {
        Intent it = new Intent(LoginActivity.this, ApplyDriverActivity.class);
        it.putExtra("title", "申请司机");
        it.putExtra("url", "http://www.baidu.com");
        it.putExtra("user", user);
        startActivity(it);
    }
}
