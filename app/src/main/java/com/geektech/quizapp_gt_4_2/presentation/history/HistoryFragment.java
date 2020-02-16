package com.geektech.quizapp_gt_4_2.presentation.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.core.CoreFragment;
import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.presentation.history.recycler.HistoryAdapter;
import com.geektech.quizapp_gt_4_2.presentation.history.recycler.HistoryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends CoreFragment implements HistoryViewHolder.Listener {

    private HistoryViewModel mViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<History> histories;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.history_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.history_recycler_view);
        recyclerBuilder();
        adapter.updateHistory(new ArrayList<>());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        mViewModel.history.observe(getActivity(), histories -> {
            this.histories = histories;
            if (histories != null) {
                adapter.updateHistory(histories);
            }
        });
    }

    private void recyclerBuilder() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        adapter = new HistoryAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void showPopupMenu(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.menu_popup);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.popup_delete:
                    App.historyStorage.deleteById(histories.get(position).getId());
                    Toast.makeText(getContext(), "You are delete", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.popup_share:
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Game: QuizApp" +
                                    "\nCategory name: " + histories.get(position).getCategoryName() +
                                    "\nCorrect answers: " + histories.get(position).getCorrectAnswers() + "/" +
                                    histories.get(position).getAmount() +
                                    "\nDifficulty: " + histories.get(position).getDifficulty() +
                                    "\nDate: " + histories.get(position).getDate());
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                    return true;
            }
            return false;
        });
        popupMenu.setOnDismissListener(menu -> popupMenu.show());
    }

    @Override
    public void onClick(View view, int position) {
        showPopupMenu(view, position);
    }
}
