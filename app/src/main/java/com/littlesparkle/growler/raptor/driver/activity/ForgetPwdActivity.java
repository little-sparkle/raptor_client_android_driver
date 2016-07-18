package com.littlesparkle.growler.raptor.driver.activity;

import com.littlesparkle.growler.library.activity.BaseForgetPwdActivity;
import com.littlesparkle.growler.library.dialog.DialogHelper;

public class ForgetPwdActivity extends BaseForgetPwdActivity {
    @Override
    protected void onSendAuthCodeClick() {
        DialogHelper.showDialogWithMessage(this, "send auth code");
    }

    @Override
    protected void onResetPwdClick() {
        DialogHelper.showDialogWithMessage(this, "reset");
    }
}
