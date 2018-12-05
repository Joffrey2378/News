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

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder viewHolder, int i) {
        News news = mBreakingNews.get(i);
        Picasso.with(mContext)
                .load(news.getmImage())
                .placeholder(R.drawable.news_icon)
                .into(viewHolder.imageView);

        viewHolder.titleView.setText((mBreakingNews.get(i).getmTitle()));
    }

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

    public interface OnItemClickListener {
        void onItemClick(int position/*, View v*/);
    }

    @Override
    public int getItemCount() {
        return mBreakingNews.size();
    }

    public /*static*/ class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.url_to_image)
        protected ImageView imageView;

        @BindView(R.id.title)
        protected TextView titleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
