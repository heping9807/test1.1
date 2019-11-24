package com.jnu.taobao;

public class Book {
    private String Nanme;


    public Book(String nanme, int coverId) {
        setNanme(nanme);
        setCoverId(coverId);
    }

    private int CoverId;


    public String getNanme() {
        return Nanme;
    }

    public void setNanme(String nanme) {
        Nanme = nanme;
    }

    public int getCoverId() {
        return CoverId;
    }

    public void setCoverId(int coverId) {
        CoverId = coverId;
    }
}
