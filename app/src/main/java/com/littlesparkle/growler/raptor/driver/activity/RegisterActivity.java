package com.littlesparkle.growler.raptor.driver.activity;

import android.content.Intent;
import android.widget.Toast;

import com.littlesparkle.growler.library.activity.BaseRegisterActivity;
import com.littlesparkle.growler.library.http.BaseHttpSubscriber;
import com.littlesparkle.growler.library.http.DefaultResponse;
import com.littlesparkle.growler.library.misc.MiscHelper;
import com.littlesparkle.growler.library.user.UserRequest;
import com.littlesparkle.growler.library.user.UserSignUpResponse;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.activity.web.ApplyDriverActivity;
import com.littlesparkle.growler.raptor.driver.backend.Api;

public class RegisterActivity extends BaseRegisterActivity {
    @Override
    protected void onSendAuthCodeClick() {
        String phoneNumber = mMobileInput.getText().toString();
        if ("".equals(phoneNumber) || !MiscHelper.checkPhoneNumber(phoneNumber)) {
            Toast.makeText(this, R.string.illegal_mobile, Toast.LENGTH_SHORT).show();
            return;
        }

        new UserRequest().signupSendSms(new BaseHttpSubscriber<DefaultResponse>(this, this) {
            @Override
            protected void onError(String message) {
                super.onError(message);
            }

            @Override
            public void onNext(DefaultResponse defaultResponse) {
            }
        }, phoneNumber);
    }

    @Override
    protected void onRegisterClick() {
        String phoneNumber = mMobileInput.getText().toString();
        if ("".equals(phoneNumber) || !MiscHelper.checkPhoneNumber(phoneNumber)) {
            Toast.makeText(this, R.string.illegal_mobile, Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = mPwdInput.getText().toString();
        String pwdConfirm = mPwdConfInput.getText().toString();
        if ("".equals(pwd) || "".equals(pwdConfirm)) {
            Toast.makeText(this, R.string.illegal_password, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(pwdConfirm)) {
            Toast.makeText(this, R.string.passwords_are_not_equal, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!MiscHelper.checkPassword(pwd)) {
            Toast.makeText(this, R.string.illegal_password, Toast.LENGTH_SHORT).show();
            return;
        }

        new UserRequest().signup(new BaseHttpSubscriber<UserSignUpResponse>(this, this) {
            @Override
            public void onNext(UserSignUpResponse driverSignUpResponse) {
                Intent it = new Intent(RegisterActivity.this, ApplyDriverActivity.class);
                it.putExtra("title", getString(R.string.apply_for_driver));
                it.putExtra("url", Api.APPLY_FOR_DRIVER);
                it.putExtra("user", driverSignUpResponse.data.user);
                startActivity(it);
            }
        }, phoneNumber, pwd, mAuthCodeInput.getText().toString());
    }
}
