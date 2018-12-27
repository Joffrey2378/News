package com.example.heorhii_dubinin.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private OnItemClickListener listener;

    private List<NewsPiece> breakingNews = new ArrayList<>();
    private Context context;

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
        NewsPiece currentNewsPiece = breakingNews.get(i);
//        Picasso.with(context)
//                .load(currentNewsPiece.getImage())
//                .placeholder(R.drawable.news_icon)
//                .into(newsHolder.imageView);

        newsHolder.titleView.setText((currentNewsPiece.getTitle()));
    }

    @Override
    public int getItemCount() {
        return breakingNews.size();
    }

    public void setBreakingNews(List<NewsPiece> breakingNews) {
        this.breakingNews = breakingNews;
        notifyDataSetChanged();
    }

//    NewsAdapter(ArrayList<NewsPiece> breakingNews, Context context) {
//        this.breakingNews = breakingNews;
//        this.context = context;
//    }

    void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(NewsPiece newsPiece);
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.url_to_image)
        ImageView imageView;

        @BindView(R.id.title)
        TextView titleView;

        NewsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(breakingNews.get(position));
                    }
                }
            });
        }
    }
}
