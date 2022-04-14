package com.example.personapp.adapter.viewHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.personapp.R;
import com.example.personapp.models.Article;
import com.example.personapp.ui.MainView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Article> mData;
    private LayoutInflater mInflater;
    MainView mainView;


    // data is passed into the constructor
    public NewsAdapter(Context context, List<Article> data, MainView mainView) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mainView=mainView;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = mData.get(position);
       // holder.myTextView.setText(animal);
        holder.title.setText(article.getTitle());
        holder.des.setText(article.getDescription());
        holder.des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mainView.onNewsItemClicked(article);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView des;
        ViewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.newsTitle);
            des= itemView.findViewById(R.id.newsDes);
        }


    }


}