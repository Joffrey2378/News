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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

//    private OnItemClickListener mListener;

    private List<News> mBreakingNews = new ArrayList<>();
    private Context mContext;

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
        News currentNews = mBreakingNews.get(i);
//        Picasso.with(mContext)
//                .load(currentNews.getImage())
//                .placeholder(R.drawable.news_icon)
//                .into(newsHolder.imageView);

        newsHolder.titleView.setText((/*mBreakingNews.get(i)*/currentNews.getTitle()));
    }

    @Override
    public int getItemCount() {
        return mBreakingNews.size();
    }

    public void setBreakingNews(List<News> breakingNews) {
        this.mBreakingNews = breakingNews;
        notifyDataSetChanged();
    }

//    void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }
//
//    NewsAdapter(ArrayList<News> breakingNews, Context context) {
//        this.mBreakingNews = breakingNews;
//        this.mContext = context;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }

    class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.url_to_image)
        ImageView imageView;

        @BindView(R.id.title)
        TextView titleView;

        NewsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            mListener.onItemClick(position);
//                        }
//                    }
//                }
//            });
        }
    }
}
