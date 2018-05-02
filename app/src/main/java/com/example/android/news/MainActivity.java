package com.example.android.news;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<NewsItem>> {
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final int GUARDIAN_LOADER_ID = 1;
    private static final String NEWS_REQUEST_URL =
            "http://content.guardianapis.com/search?q=debates&api-key=test&show-tags=contributor";
    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private ProgressBar mProgressBar;
    ListView newsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.message_text_View);
        newsListView.setEmptyView(mEmptyStateTextView);
        mAdapter = new NewsAdapter(this, new ArrayList<NewsItem>());
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        newsListView.setAdapter(mAdapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsItem currentNews = mAdapter.getItem(position);
                Uri NewsUri = Uri.parse(currentNews.getWebURL());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NewsUri);
                startActivity(websiteIntent);

            }
        });


        if (isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(GUARDIAN_LOADER_ID, null, this);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public Loader<List<NewsItem>> onCreateLoader(int id, Bundle args) {

        Uri baseUri = Uri.parse(NEWS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsItem>> loader, final List<NewsItem> newsItems) {
        View loadingIndicator = findViewById(R.id.progress_bar);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_news);
        mAdapter.clear();
        if (newsItems != null && !newsItems.isEmpty()) {
            mAdapter.addAll(newsItems);

        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsItem>> loader) {
        mAdapter.clear();
    }
}