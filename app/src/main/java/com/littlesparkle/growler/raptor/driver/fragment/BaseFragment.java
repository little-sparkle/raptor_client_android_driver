package com.littlesparkle.growler.raptor.driver.fragment;

import android.support.v4.app.Fragment;

import com.littlesparkle.growler.raptor.driver.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BaseFragment extends Fragment {
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
        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText(message)
                .setConfirmText(getString(R.string.ok))
                .setConfirmClickListener(listener)
                .show();
    }
}
