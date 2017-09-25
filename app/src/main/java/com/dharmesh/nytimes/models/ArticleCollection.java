package com.dharmesh.nytimes.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dgohil on 9/20/17.
 */

public class ArticleCollection {

    @SerializedName("docs")
    private List<Article> articles;
    private ArticleMeta meta;

    public ArticleMeta getMeta() {
        return meta;
    }

    public void setMeta(ArticleMeta meta) {
        this.meta = meta;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
