package com.geektech.quizapp_gt_4_2.data.db.converters;

import androidx.room.TypeConverter;

import java.util.Date;

public class TimestampConverter {
    @TypeConverter
    public static Date fromTimestamp(Long time) {
        if (time == null) return null;

        return new Date(time);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? 0L : date.getTime();
    }
}
