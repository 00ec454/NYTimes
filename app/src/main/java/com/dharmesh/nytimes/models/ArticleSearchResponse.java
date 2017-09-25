package com.dharmesh.nytimes.models;

/**
 * Created by dgohil on 9/20/17.
 */

public class ArticleSearchResponse {
    private String status;
    private ArticleCollection response;
    private String copyright;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArticleCollection getResponse() {
        return response;
    }

    public void setResponse(ArticleCollection response) {
        this.response = response;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
