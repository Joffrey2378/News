package com.example.heorhii_dubinin.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends ArrayAdapter<News> {

    @BindView(R.id.url_to_image)
    ImageView urlToImageView;

    @BindView(R.id.title)
    TextView titleView;

    @BindView(R.id.published_at)
    TextView publishedAtView;

    public NewsAdapter(Context context, List<News> breakingNews) {
        super(context, 0, breakingNews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ButterKnife.bind(this, listItemView);
        News currentNews = getItem(position);

//        urlToImageView.setText(currentNews.getmImage());
        Picasso.with(getContext()).load(currentNews.getmImage()).placeholder(R.drawable.ic_launcher_background).into(urlToImageView);
        titleView.setText(currentNews.getmTitle());
        publishedAtView.setText(currentNews.getmPublishedAt());

        return listItemView;
    }
}
