package com.geektech.quizapp_gt_4_2.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geektech.quizapp_gt_4_2.data.history.HistoryDao;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

@Database(entities = {QuizResult.class},
        version = 3,
        exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();
}
