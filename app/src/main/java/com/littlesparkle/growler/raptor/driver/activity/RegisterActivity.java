package com.littlesparkle.growler.raptor.driver.activity;

import com.littlesparkle.growler.library.activity.BaseRegisterActivity;
import com.littlesparkle.growler.library.dialog.DialogHelper;
import com.littlesparkle.growler.library.http.BaseHttpSubscriber;
import com.littlesparkle.growler.library.http.DefaultResponse;
import com.littlesparkle.growler.library.http.api.ApiException;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.misc.MiscHelper;
import com.littlesparkle.growler.library.user.UserRequest;

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
        new UserRequest().signupSendSms(new BaseHttpSubscriber<DefaultResponse>() {
            @Override
            protected void onError(ApiException err) {
                Logger.log("#####", err.toString());
            }

            @Override
            public void onNext(DefaultResponse defaultResponse) {
                Logger.log("#####", defaultResponse.toString());
            }
        }, phoneNumber);
    }

    @Override
    protected void onRegisterClick() {
        DialogHelper.showDialogWithMessage(this, "register");
    }
}
