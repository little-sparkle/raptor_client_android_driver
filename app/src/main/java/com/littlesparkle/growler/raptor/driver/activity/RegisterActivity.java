package com.littlesparkle.growler.raptor.driver.activity;

import com.littlesparkle.growler.library.activity.BaseRegisterActivity;
import com.littlesparkle.growler.library.http.BaseHttpSubscriber;
import com.littlesparkle.growler.library.http.DefaultResponse;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.misc.MiscHelper;
import com.littlesparkle.growler.library.user.UserRequest;
import com.littlesparkle.growler.library.user.UserSignUpResponse;

public class RegisterActivity extends BaseRegisterActivity {
    @Override
    protected void onSendAuthCodeClick() {
        String phoneNumber = mMobileInput.getText().toString();
        if ("".equals(phoneNumber)) {
            return;
        }
        if (!MiscHelper.checkPhoneNumber(phoneNumber)) {
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
        if ("".equals(phoneNumber)) {
            return;
        }
        if (!MiscHelper.checkPhoneNumber(phoneNumber)) {
            return;
        }

        String pwd = mPwdInput.getText().toString();
        String pwdConfirm = mPwdConfInput.getText().toString();
        if ("".equals(pwd) || "".equals(pwdConfirm)) {
            return;
        }
        if (!pwd.equals(pwdConfirm)) {
            return;
        }
        if (!MiscHelper.checkPassword(pwd)) {
            return;
        }

        new UserRequest().signup(new BaseHttpSubscriber<UserSignUpResponse>(this, this) {
            @Override
            protected void onError(String message) {
                super.onError(message);
                Logger.log("signup error: " + message);
            }

            @Override
            public void onNext(UserSignUpResponse userSignUpResponse) {
                Logger.log("User: " + userSignUpResponse.toString());
            }
        }, phoneNumber, pwd, "1234");
    }
}
