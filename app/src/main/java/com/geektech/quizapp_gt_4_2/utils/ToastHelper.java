package com.geektech.quizapp_gt_4_2.utils;

import android.widget.Toast;

import com.geektech.quizapp_gt_4_2.App;

public class ToastHelper {
    public static void show(String msg){
        Toast.makeText(App.instance, msg, Toast.LENGTH_LONG).show();
    }
}
