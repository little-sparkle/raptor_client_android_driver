package com.littlesparkle.growler.raptor.driver.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.littlesparkle.growler.raptor.driver.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showDialogWithMessage(final String message) {
        showDialogWithMessage(null, message);
    }

    protected void showDialogWithMessage(
            SweetAlertDialog.OnSweetClickListener listener,
            final String message) {
        if (listener == null) {
            listener = new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            };
        }
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(message)
                .setConfirmText(getString(R.string.ok))
                .setConfirmClickListener(listener)
                .show();
    }
}
