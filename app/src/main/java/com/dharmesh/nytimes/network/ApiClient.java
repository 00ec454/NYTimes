package com.dharmesh.nytimes.network;


import android.util.Log;

import com.dharmesh.nytimes.models.ArticleSearchResponse;
import com.dharmesh.nytimes.models.SearchRequest;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import static com.dharmesh.nytimes.network.NYTimesService.BASE_URL;

/**
 * Created by dgohil on 9/20/17.
 */

public class ApiClient {

    private static final String TAG = ApiClient.class.getSimpleName();
    private static ApiClient instance;
    private Retrofit retrofit;
    private NYTimesService service;
    private static String API_KEY = "e30f3ca401354b329406bf47310532fe";


    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    private ApiClient() {
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(NYTimesService.class);
    }

    public Call<ArticleSearchResponse> searchArticles(SearchRequest query) {
        Log.i(TAG, String.format("%s,%s,%s,%s", query.getQuery(), query.getBeginDate(), query.getNewsDesksAsString(), query.getOrderBy()));
        return service.searchArticles(query.getQuery(), query.getBeginDate(), query.getNewsDesksAsString(), query.getOrderBy(), API_KEY);
    }
}
