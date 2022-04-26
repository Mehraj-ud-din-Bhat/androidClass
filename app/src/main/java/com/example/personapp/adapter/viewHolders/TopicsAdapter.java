package com.example.personapp.adapter.viewHolders;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personapp.R;
import com.example.personapp.models.Topic;
import com.example.personapp.ui.MainView;

import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    private List<Topic> mData;
    private LayoutInflater mInflater;
    MainView mainView;
    Context context;



    // data is passed into the constructor
    public TopicsAdapter(Context context, List<Topic> data, MainView mainView) {
      this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mainView=mainView;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.topic_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Topic topic= mData.get(position);
//       // holder.myTextView.setText(animal);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(6, 6, 6, 6);
        holder.root.setLayoutParams(params);
        if(topic.isSelected())
        {
            holder.title.setTextColor(context.getColor(R.color.white));
            holder.root.setBackground(context.getDrawable(R.drawable.bg_topic_selected));
            holder.root.setPadding(30,15,30,15);
        }else {
            holder.title.setTextColor(context.getColor(R.color.black));
            holder.root.setBackground(context.getDrawable(R.drawable.bg_topic));
            holder.root.setPadding(30,15,30,15);
        }
        holder.title.setText(topic.getTopicName());
        holder.root.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                for(Topic topic:mData)
                {
                    topic.setSelected(false);
                }
                topic.setSelected(true);
                notifyDataSetChanged();
                mainView.onTopicClicked(topic.getTopicName());

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
        View root;
        TextView title;
        TextView des;

        ViewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.topicName);
            root=itemView.findViewById(R.id.root);
           // des= itemView.findViewById(R.id.newsDes);
        }


    }


}