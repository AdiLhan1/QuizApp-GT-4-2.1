package com.geektech.quizapp_gt_4_2.main;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;

import java.util.Objects;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private TextView textView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.main_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SeekBar seekBar = view.findViewById(R.id.seekBar);
        textView = view.findViewById(R.id.amount);
        seekBar.setProgress(25);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(MainViewModel.class);

    }

}
