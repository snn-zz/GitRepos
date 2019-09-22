package com.example.gitrepos.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class DeviceScreenUtil {

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
