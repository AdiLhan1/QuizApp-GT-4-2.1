package com.geektech.quizapp_gt_4_2.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.quiz.QuizActivity;
import com.geektech.quizapp_gt_4_2.utils.SeekBarChangeHelper;

import java.util.Objects;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private TextView textView;
    private Button btnstart;

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
        btnstart = view.findViewById(R.id.start_button);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QuizActivity.class));
            }
        });
        seekBar.setProgress(25);
        seekBar.setOnSeekBarChangeListener(new SeekBarChangeHelper() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("------------", progress + "");
                textView.setText(String.valueOf(progress));
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
