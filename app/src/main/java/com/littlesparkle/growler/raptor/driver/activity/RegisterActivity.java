package com.littlesparkle.growler.raptor.driver.activity;

import com.littlesparkle.growler.library.activity.BaseRegisterActivity;
import com.littlesparkle.growler.library.dialog.DialogHelper;

public class RegisterActivity extends BaseRegisterActivity {
    @Override
    protected void onSendAuthCodeClick() {
        DialogHelper.showDialogWithMessage(this, "send auth code");
    }

    @Override
    protected void onRegisterClick() {
        DialogHelper.showDialogWithMessage(this, "register");
    }
}
