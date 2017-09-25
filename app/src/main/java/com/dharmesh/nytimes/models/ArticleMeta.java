package com.dharmesh.nytimes.models;

/**
 * Created by dgohil on 9/20/17.
 */

public class ArticleMeta {
    private int hits;
    private int offset;
    private int time;

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
