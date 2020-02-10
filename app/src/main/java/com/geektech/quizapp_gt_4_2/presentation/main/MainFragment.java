package com.geektech.quizapp_gt_4_2.presentation.main;

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
import com.geektech.quizapp_gt_4_2.presentation.quiz.QuizActivity;
import com.geektech.quizapp_gt_4_2.utils.SeekBarChangeHelper;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private TextView textView;
    private Button btnstart;
    private NiceSpinner categorySpinner, difficultySpinner;
    private SeekBar amountSeekbar;
    private String difficult;
    private Integer category;

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
        amountSeekbar = view.findViewById(R.id.seekBar);
        categorySpinner = view.findViewById(R.id.category_spinner);
        difficultySpinner = view.findViewById(R.id.difficulty_spinner);
        textView = view.findViewById(R.id.amount);
        btnstart = view.findViewById(R.id.start_button);
        setSpinners(getResources().getStringArray(R.array.category_list), categorySpinner);
        setSpinners(getResources().getStringArray(R.array.difficulty_list), difficultySpinner);

        amountSeekbar.setProgress(25);
        amountSeekbar.setOnSeekBarChangeListener(new SeekBarChangeHelper() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("------------", progress + "");
                textView.setText(String.valueOf(progress));
            }
        });
    }

    private String getDifficultyId() {
        switch (difficultySpinner.getSelectedIndex()) {
            case 0:
                difficult = null;
                break;
            case 1:
                difficult = "easy";
                break;
            case 2:
                difficult = "medium";
                break;
            case 3:
                difficult = "hard";
                break;
        }
        return difficult;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(MainViewModel.class);
        btnstart.setOnClickListener(v -> {
            category = categorySpinner.getSelectedIndex() + 8;
            difficultySpinner.getSelectedIndex();
            QuizActivity.start(
                    requireContext(),
                    amountSeekbar.getProgress(),
                    category,
                    getDifficultyId());
        });
        mViewModel.finishEvent.observe(this, aVoid -> {
            Log.d("ololo", "Finish");
        });

        mViewModel.messageEvent.observe(this, message -> {
            Log.d("ololo", "Meessage " + message);
        });

        mViewModel.callFinish();
        mViewModel.onShowMessageClick();
    }

    private void setSpinners(String[] array, NiceSpinner spinner) {
        List<String> list = new LinkedList<>(Arrays.asList(array));
        spinner.attachDataSource(list);
    }

}
