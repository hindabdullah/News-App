package com.example.android.news;

/**
 * Created by hind on 28/01/18.
 */

public class NewsItem {
    private String mWebTitle;
    private String mDate;
    private String mSection;
    private String newsAuthor;
    private String mWebURL;

    public NewsItem(String webTitle, String Date, String author, String Section, String WebURL) {
        this.mWebTitle = webTitle;
        this.newsAuthor = author;
        this.mDate = Date;
        this.mSection = Section;
        this.mWebURL = WebURL;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getSection() {
        return mSection;
    }

    public String getWebURL() {
        return mWebURL;
    }

    public String getAuthor() {
        return newsAuthor;
    }
}