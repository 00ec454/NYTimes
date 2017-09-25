package com.dharmesh.nytimes.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dgohil on 9/20/17.
 */

public class ArticleHeadline {

    private String main;
    @SerializedName("print_headline")
    private String printHeadline;
    private String kicker;

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }
}
