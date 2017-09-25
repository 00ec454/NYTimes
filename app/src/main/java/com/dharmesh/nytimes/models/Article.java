package com.dharmesh.nytimes.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by dgohil on 9/19/17.
 */

public class Article {

    @SerializedName("web_url")
    private String webUrl;
    private String snippet;
    private String source;
    private ArticleHeadline headline;
    @SerializedName("pub_date")
    private Date pubDate;
    @SerializedName("document_type")
    private String documentType;
    private List<ArticleKeyword> keywords;
    @SerializedName("new_desk")
    private String newDesk;
    @SerializedName("section_name")
    private String sectionName;
    @SerializedName("type_of_material")
    private String typeOfMaterial;
    private String _id;
    private String uri;
    private float score;
    @SerializedName("word_count")
    private int wordCount;
    @SerializedName("multimedia")
    private List<ArticleMultiMedia> multiMedia;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ArticleHeadline getHeadline() {
        return headline;
    }

    public void setHeadline(ArticleHeadline headline) {
        this.headline = headline;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public List<ArticleKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<ArticleKeyword> keywords) {
        this.keywords = keywords;
    }

    public String getNewDesk() {
        return newDesk;
    }

    public void setNewDesk(String newDesk) {
        this.newDesk = newDesk;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public List<ArticleMultiMedia> getMultiMedia() {
        return multiMedia;
    }

    public void setMultiMedia(List<ArticleMultiMedia> multiMedia) {
        this.multiMedia = multiMedia;
    }
}
