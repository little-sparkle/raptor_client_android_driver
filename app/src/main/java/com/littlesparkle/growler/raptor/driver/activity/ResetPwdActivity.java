package com.littlesparkle.growler.raptor.driver.activity;

import com.littlesparkle.growler.library.activity.BaseForgetPwdActivity;
import com.littlesparkle.growler.library.activity.BaseResetPwdActivity;
import com.littlesparkle.growler.library.dialog.DialogHelper;

public class ResetPwdActivity extends BaseResetPwdActivity {
    @Override
    protected void onResetPwdClick() {
        DialogHelper.showDialogWithMessage(this, "reset");
    }
}
