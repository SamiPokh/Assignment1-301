package com.example.samippya_emotilog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Simple data class to store an emotion log entry
 */
public class EmoticonLog {
    private String emoticon;
    private String emotionName;
    private long timestamp;

    public EmoticonLog(String emoticon, String emotionName) {
        this.emoticon = emoticon;
        this.emotionName = emotionName;
        this.timestamp = System.currentTimeMillis();
    }

    public String getEmoticon() {
        return emoticon;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    public String getLogDisplay() {
        return emoticon + " " + emotionName + " " + getFormattedTime();
    }
}