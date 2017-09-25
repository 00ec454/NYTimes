package com.dharmesh.nytimes.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgohil on 9/23/17.
 */

public class SearchRequest implements Serializable {
    private String orderBy = "Newest";
    private List<String> newsDesks = new ArrayList<>();
    private String beginDate;
    private String query;
    DateFormat DF1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat DF2 = new SimpleDateFormat("yyyyMMdd");

    public String getOrderBy() {
        return orderBy.toLowerCase();
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<String> getNewsDesks() {
        return newsDesks;
    }

    public String getNewsDesksAsString() {
        if (newsDesks.isEmpty()) {
            return null;
        }
        StringBuilder newsDeskOptions = new StringBuilder("news_desk:(");
        for (String newDesk : newsDesks) {
            newsDeskOptions.append("\"").append(newDesk).append("\" ");
        }
        return newsDeskOptions.append(")").toString();
    }

    public void setNewsDesks(List<String> newsDesks) {
        this.newsDesks = newsDesks;
    }

    public void addNewDesk(String newDeskItem) {
        if (newDeskItem != null) {
            this.newsDesks.add(newDeskItem);
        }
    }

    public String getBeginDate() {
        try {
            System.out.println("beginDate :: "+beginDate);
            return DF2.format(DF1.parse(beginDate));
        } catch (Exception e) {
            return null;
        }
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
