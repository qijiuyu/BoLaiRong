package com.bian.dan.blr.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ImmSolftManager {
    private Activity activity;
    InputMethodManager imm;
    public ImmSolftManager(Activity activity){
        this.activity = activity;
        imm = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void showKeyboard(boolean isShow) {
        //当前获取到焦点的view
        View currentFocus = activity.getCurrentFocus();
        if (null == imm)
            return;

        if (isShow) {
            if (currentFocus != null) {
                //有焦点打开
                imm.showSoftInput(currentFocus, 0);
            } else {
                //无焦点打开
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        } else {
            if (currentFocus != null) {
                //有焦点关闭
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                //无焦点关闭
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        }
    }
}
