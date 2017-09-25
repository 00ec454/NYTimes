package com.dharmesh.nytimes.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dharmesh.nytimes.R;
import com.dharmesh.nytimes.adapters.ArticleAdapter;
import com.dharmesh.nytimes.fragments.DatePickerFragment;
import com.dharmesh.nytimes.models.Article;
import com.dharmesh.nytimes.models.ArticleSearchResponse;
import com.dharmesh.nytimes.models.SearchRequest;
import com.dharmesh.nytimes.network.ApiClient;
import com.vistrav.pop.Pop;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndexActivity extends AppCompatActivity {


    private static final String TAG = IndexActivity.class.getSimpleName();
    private ArticleAdapter articleAdapter;
    private static final DateFormat DF = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
    private SearchRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        RecyclerView rvArticles = findViewById(R.id.rvArticles);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvArticles.setLayoutManager(mLayoutManager);
        articleAdapter = new ArticleAdapter(this);
        rvArticles.setAdapter(articleAdapter);
        request = new SearchRequest();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.index, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_article).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                request.setQuery(query);
                searchArticles(request);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private void searchArticles(SearchRequest searchRequest) {
        ApiClient client = ApiClient.getInstance();
        Call<ArticleSearchResponse> response = client.searchArticles(searchRequest);
        response.enqueue(new Callback<ArticleSearchResponse>() {
            @Override
            public void onResponse(Call<ArticleSearchResponse> call, Response<ArticleSearchResponse> response) {
                if (response.body() != null && response.body().getResponse() != null) {
                    List<Article> articles = response.body().getResponse().getArticles();
                    articleAdapter.setArticles(articles);
                }
            }

            @Override
            public void onFailure(Call<ArticleSearchResponse> call, Throwable t) {

            }
        });
    }

    public void filterArticles(MenuItem item) {
        Pop.on(this)
                .layout(R.layout.article_filter)
                .when((Pop.Yah) (dialog, view) -> saveFilterData(view))
                .when((Pop.Nah) (dialog, view) -> dialog.dismiss())
                .show(this::buildFiledView);
    }

    private void saveFilterData(View view) {
        Spinner spSortType = view.findViewById(R.id.spSortType);
        CheckBox cbArts = view.findViewById(R.id.cbArts);
        CheckBox cbFashionType = view.findViewById(R.id.cbFashionType);
        CheckBox cbSports = view.findViewById(R.id.cbSports);
        TextView tvBeginDate = view.findViewById(R.id.tvBeginDate);

        request.setBeginDate(tvBeginDate.getText().toString());
        request.setOrderBy(spSortType.getSelectedItem().toString());
        request.setNewsDesks(new ArrayList<>());
        request.addNewDesk(cbArts.isChecked() ? "Arts" : null);
        request.addNewDesk(cbSports.isChecked() ? "Sports" : null);
        request.addNewDesk(cbFashionType.isChecked() ? "Fashion & Style" : null);
        searchArticles(request);
    }

    private void buildFiledView(View view) {
        final TextView tvBeginDate = view.findViewById(R.id.tvBeginDate);
        final DateListener listener = calendar -> tvBeginDate.setText(DF.format(calendar.getTime()));
        tvBeginDate.setOnClickListener(view1 -> {
            DatePickerFragment newFragment = new DatePickerFragment();
            newFragment.show(getFragmentManager(), "datePicker");
            newFragment.setListener(listener);
        });

    }

    public interface DateListener {
        void beginDate(Calendar calendar);
    }
}

