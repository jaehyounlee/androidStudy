package com.dbins.android.hellowandroid.util;

import android.text.TextUtils;
import android.util.Patterns;
public class validator {
    public final static boolean isValidEmailForm(CharSequence target) {
            if(TextUtils.isEmpty(target)) {
                return false;
            } else {
                return Patterns.EMAIL_ADDRESS.matcher(target).matches();
            }
    }
}
