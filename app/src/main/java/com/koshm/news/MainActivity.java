package com.koshm.news;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.koshm.news.Adapter.OnItemClickListener;
import com.koshm.news.api.ApiClient;
import com.koshm.news.api.ApiInterface;
import com.koshm.news.models.Article;
import com.koshm.news.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String API_KEY = "0a89fca3a8c44b00817030eba81542bd";
    private String TAG = MainActivity.class.getSimpleName();
    public Adapter adapter;
    public List<Article> articles = new ArrayList();
    private RecyclerView.LayoutManager layoutManager;
    public RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView topHeadline;
    public static DatabaseController databaseController;
    private boolean firstStart = true;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        this.swipeRefreshLayout.setOnRefreshListener(this);
        this.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        this.topHeadline = findViewById(R.id.topheadelines);
        this.recyclerView = findViewById(R.id.recyclerView);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setNestedScrollingEnabled(false);
        onLoadingSwipeRefresh("");
        databaseController = new DatabaseController(this);
    }

    public void LoadJson(String keyword) {
        swipeRefreshLayout.setRefreshing(true);
        Call<News> call;

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String country = Utils.getCountry();
        String language = Utils.getLanguage();
        if (keyword.length() > 0) {
            call = apiInterface.getNewsSearch(keyword, country, "publishedAt", API_KEY);
        } else {
            call = apiInterface.getNews(country, API_KEY);
        }
        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (!response.isSuccessful() || ((News) response.body()).getArticle() == null) {
                    topHeadline.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "No Result!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!articles.isEmpty()) {
                    articles.clear();
                }
                articles = ((News) response.body()).getArticle();
                adapter = new Adapter(MainActivity.this.articles, MainActivity.this);
                recyclerView.setAdapter(MainActivity.this.adapter);
                adapter.notifyDataSetChanged();

                topHeadline.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                initListener();
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                topHeadline.setVisibility(View.INVISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void initListener() {
        this.adapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);

                Article article = articles.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title", article.getTitle());
                intent.putExtra("img", article.getUrlToImage());
                intent.putExtra("date", article.getPublishedAt());
                intent.putExtra("source", article.getSource().getName());
                intent.putExtra("author", article.getAuthor());

                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Latest News...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 2) {
                    onLoadingSwipeRefresh(query);
                }
                return true;
            }

            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);

        MenuItem resent = menu.findItem(R.id.action_resent);
        onOptionsItemSelected(resent);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.action_resent == item.getItemId() && !firstStart) {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        }else{
            firstStart = false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        LoadJson("");
    }

    private void onLoadingSwipeRefresh(final String key) {
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        LoadJson(key);
                    }
                }
        );
    }
}
