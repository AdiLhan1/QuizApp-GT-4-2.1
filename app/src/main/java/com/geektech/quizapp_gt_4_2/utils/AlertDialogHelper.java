package com.geektech.quizapp_gt_4_2.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogHelper {
    public static void createAlertDialog(Context context, String title, String msg) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)

                .setPositiveButton("Да", (dialog, which) -> {
                })

                .setNegativeButton("Нет", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
