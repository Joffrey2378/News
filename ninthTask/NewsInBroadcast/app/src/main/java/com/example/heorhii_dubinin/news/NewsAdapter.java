package com.example.heorhii_dubinin.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> mBreakingNews;
    private Context mContext;

    NewsAdapter(ArrayList<News> breakingNews, Context context) {
        this.mBreakingNews = breakingNews;
        this.mContext = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder viewHolder, int i) {
        News news = mBreakingNews.get(i);
        Picasso.with(mContext).load(news.getmImage()).placeholder(R.drawable.ic_launcher_background).into(viewHolder.imageView);
        viewHolder.titleView.setText((mBreakingNews.get(i).getmTitle()));
    }

    @Override
    public int getItemCount() {
        return mBreakingNews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.url_to_image)
        ImageView imageView;

        @BindView(R.id.title)
        TextView titleView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }
}
