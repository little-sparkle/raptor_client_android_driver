package com.littlesparkle.growler.raptor.driver.activity;

import com.littlesparkle.growler.library.activity.BaseRegisterActivity;
import com.littlesparkle.growler.library.http.BaseHttpSubscriber;
import com.littlesparkle.growler.library.http.DefaultResponse;
import com.littlesparkle.growler.library.misc.MiscHelper;
import com.littlesparkle.growler.library.user.UserRequest;
import com.littlesparkle.growler.library.user.driver.DriverRequest;
import com.littlesparkle.growler.library.user.driver.DriverSignUpResponse;

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

        new DriverRequest().signup(new BaseHttpSubscriber<DriverSignUpResponse>(this, this) {
            @Override
            public void onNext(DriverSignUpResponse driverSignUpResponse) {
            }
        }, phoneNumber, pwd, "1234");
    }
}
