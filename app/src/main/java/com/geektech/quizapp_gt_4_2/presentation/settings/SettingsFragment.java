package com.geektech.quizapp_gt_4_2.presentation.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.BuildConfig;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;

import java.util.Objects;

public class SettingsFragment extends CoreFragment {

    private SettingsViewModel settingsViewModel;
    private TextView tvClearHistory, tvLeaveFeedback, tvShare, tv_RateUs;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.settings_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        tvClearHistory = view.findViewById(R.id.settings_clear_history);
        tvLeaveFeedback = view.findViewById(R.id.settings_leave_feedback);
        tvShare = view.findViewById(R.id.settings_share_this_app);
        tv_RateUs = view.findViewById(R.id.settings_rate_us);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingsViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(SettingsViewModel.class);
        clickListener();

    }

    private void clickListener() {
        tvClearHistory.setOnClickListener(v -> new AlertDialog.Builder(requireContext())
                .setTitle("Очистка Истории")
                .setMessage("Вы точно хотите очистить всю историю?")

                .setPositiveButton("Да", (dialog, which) -> {
                    App.historyStorage.deleteAll();
                })

                .setNegativeButton("Нет", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());
        tvShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        });
    }

}
