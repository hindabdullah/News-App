package com.example.android.news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by hind on 28/01/18.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {
    private static final String LOG_TAG = NewsLoader.class.getName();

    private String mURL;

    public NewsLoader(MainActivity context, String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsItem> loadInBackground() {
        if (mURL == null) {
            return null;
        }
        List<NewsItem> newsItems = QueryUtils.fetchData(mURL);
        return newsItems;

    }
}
