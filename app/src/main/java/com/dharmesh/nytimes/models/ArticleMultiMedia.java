package com.dharmesh.nytimes.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dgohil on 9/20/17.
 */

public class ArticleMultiMedia {
    private String type;
    @SerializedName("sub_type")
    private String subType;
    private String url;
    private int height;
    private int width;
    private int rank;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
