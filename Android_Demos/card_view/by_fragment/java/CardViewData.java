package com.example.a61555.cardviewdemo;

import java.io.Serializable;

/**
 * Created by 61555 on 2017/7/18.
 */

public class CardViewData implements Serializable{

    private String title;
    private String subhead;
    private int bgColor;
    private String supText;

    public CardViewData(String title, String subhead, int bgColor, String supText) {
        this.title = title;
        this.subhead = subhead;
        this.bgColor = bgColor;
        this.supText = supText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public String getSupText() {
        return supText;
    }

    public void setSupText(String supText) {
        this.supText = supText;
    }
}
