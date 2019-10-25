package com.example.exercise01.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ViewUtils {
    public static void loadImage(String url, ImageView view) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    public static void loadCircleImage(String url, ImageView view) {
        Glide.with(view.getContext()).load(url).circleCrop().into(view);
    }
}
