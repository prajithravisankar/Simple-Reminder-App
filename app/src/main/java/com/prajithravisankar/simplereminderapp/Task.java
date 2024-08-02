package com.prajithravisankar.simplereminderapp;


import android.os.Build;

import androidx.annotation.NonNull;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task {

    private String taskName;
    private LocalDateTime dueDateTime;

    public Task(String taskName, LocalDateTime dueDateTime) {
        this.taskName = taskName;
        this.dueDateTime = dueDateTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    // EFFECTS: returns the remaining time in days, hours, min, seconds format
    public String getRemainingTime() {
        LocalDateTime currentTime;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentTime = LocalDateTime.now();
            long seconds = Duration.between(currentTime, dueDateTime).getSeconds();
            long days = seconds / (24 * 3600);
            seconds %= (24 * 3600);
            long hours = seconds / 3600;
            seconds %= 3600;
            long minutes = seconds / 60;
            seconds %= 60;
            return days + "d:" + hours + "hr:" + minutes + "min:" + seconds + "sec";
        } else {
            return "unsupported android version";
        }
    }

    @NonNull
    @Override
    public String toString() {
        return this.taskName;
    }
}
