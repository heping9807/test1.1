package com.example.time1;

public class Time {
    private String title;
    private String time;
    private String beizhu;

    public Time(String title, String time, String beizhu,int coverResourceId) {
        this.setTitle(title);
        this.setTime(time);
        this.setBeizhu(beizhu);
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

    public String getBeizhu() {
        return beizhu;
    }
    public void setBeizhu(String beizhu) { this.beizhu = beizhu; }


    public int getCoverResourceId() {
        return coverResourceId;
    }
    public void setCoverResourceId(int coverResourceId) {
        this.coverResourceId = coverResourceId;
    }
}
