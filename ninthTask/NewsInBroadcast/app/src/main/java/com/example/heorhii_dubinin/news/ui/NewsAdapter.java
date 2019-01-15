package com.example.heorhii_dubinin.news.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heorhii_dubinin.articleEntities.R;
import com.example.heorhii_dubinin.news.persistence.ArticleEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private OnItemClickListener listener;

    private List<ArticleEntity> breakingNews = new ArrayList<>();

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder newsHolder, int i) {
        ArticleEntity currentArticleEntity = breakingNews.get(i);
        Picasso.with(newsHolder.itemView.getContext())
                .load(currentArticleEntity.getImage())
                .placeholder(R.drawable.news_icon)
                .into(newsHolder.imageView);

        newsHolder.titleView.setText((currentArticleEntity.getTitle()));
    }

    @Override
    public int getItemCount() {
        return breakingNews.size();
    }

    void setBreakingNews(List<ArticleEntity> breakingNews) {
        this.breakingNews = breakingNews;
        notifyDataSetChanged();
    }

    void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ArticleEntity articleEntity);
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.url_to_image)
        ImageView imageView;

        @BindView(R.id.title)
        TextView titleView;

        NewsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(breakingNews.get(position));
                }
            });
        }
    }
}
