package com.example.time1;

public class Time {
    private String title;
    private String time;

    public Time(String title, String time, int coverResourceId) {
        this.setTitle(title);
        this.setTime(time);
        this.setCoverResourceId(coverResourceId);
    }

    private int coverResourceId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCoverResourceId() {
        return coverResourceId;
    }

    public void setCoverResourceId(int coverResourceId) {
        this.coverResourceId = coverResourceId;
    }
}
