package com.dharmesh.nytimes.network;

import com.dharmesh.nytimes.models.ArticleSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dgohil on 9/19/17.
 */

public interface NYTimesService {

    String BASE_URL = "https://api.nytimes.com";
    String SEARCH_URL = "svc/search/v2/articlesearch.json";

    @GET(SEARCH_URL)
    Call<ArticleSearchResponse> searchArticles(
            @Query("q") String query,
            @Query("begin_date") String beginDate,
            @Query("fq") String fq,
            @Query("sort") String sortType,
            @Query("api-key") String apiKey);
}

