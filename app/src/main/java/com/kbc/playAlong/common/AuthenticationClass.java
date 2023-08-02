package com.kbc.playAlong.common;

import android.text.TextUtils;

public class AuthenticationClass {
    public static String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && target.matches(EmailPattern));
    }
}
