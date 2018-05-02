package com.example.android.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hind on 28/01/18.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    public static final String LOG_TAG = NewsAdapter.class.getName();

    public NewsAdapter(MainActivity context, ArrayList<NewsItem> items) {
        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        if (position < getCount()) {
            NewsItem currentItem = getItem(position);
            //this displays the title of news in the list
            TextView tw_title = (TextView) listItemView.findViewById(R.id.news_title);
            tw_title.setText(currentItem.getWebTitle());
            //this displays the author
            TextView tw_authors = (TextView) listItemView.findViewById(R.id.author);
            tw_authors.setText(currentItem.getAuthor());
            //this displays the section
            TextView tw_section = (TextView) listItemView.findViewById((R.id.news_section));
            tw_section.setText(currentItem.getSection());
            //this displays the date of publication
            TextView dateView = (TextView) listItemView.findViewById(R.id.news_date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.US);
            try {
                Date date = dateFormat.parse(currentItem.getDate());
                String parsedaDate = dateFormat2.format(date);
                dateView.setText(parsedaDate);
            } catch (ParseException e) {
            }
        }
        return listItemView;
    }
}