package com.example.exercise01.util;

import android.app.Activity;
import android.content.Intent;

public class ActivityUtils {
    public static void startActivityAtRoot(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.finish();
    }
}
