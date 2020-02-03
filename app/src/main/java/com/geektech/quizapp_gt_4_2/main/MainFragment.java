package com.geektech.quizapp_gt_4_2.main;

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

import org.angmarch.views.NiceSpinner;

import java.util.Objects;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private TextView textView;
    private Button btnstart;
    private NiceSpinner categorySpinner, difficultySpinner;
    private SeekBar amountSeekbar;
    private String difficult;

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
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.start(
                        requireContext(),
                        amountSeekbar.getProgress(),
                        difficultySpinner.getSelectedIndex() + 8,
                        getDifficultyId());
                Log.e("TAG", "Start properties : " + amountSeekbar.getProgress() + "-" +
                        categorySpinner.getSelectedItem().toString() + "-" +
                        difficultySpinner.getSelectedItem().toString());

            }
        });
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
            case 1:
                difficult = null;
                break;
            case 2:
                difficult = "easy";
                break;
            case 3:
                difficult = "medium";
                break;
            case 4:
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
    }

}
