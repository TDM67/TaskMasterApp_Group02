package com.example.taskmanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.models.Category;
import com.example.taskmanager.models.Notification;

import java.util.Collections;
import java.util.List;

public class MyNotificationsAdapter extends RecyclerView.Adapter<MyNotificationsAdapter.MyList> {
    List<Notification> list = Collections.emptyList();
    Context mContext;
    public MyNotificationsAdapter(List<Notification> list, Context context){
        this.list = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new MyNotificationsAdapter.MyList(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyList holder, int position) {
        Notification notification = list.get(position);
        holder.tvTitle.setText(notification.getTitle());
        if(notification.getPriority() == 1)
            holder.view_label.setBackgroundColor(mContext.getResources().getColor(R.color.high));
        else if(notification.getPriority() == 2)
            holder.view_label.setBackgroundColor(mContext.getResources().getColor(R.color.normal));
        else
            holder.view_label.setBackgroundColor(mContext.getResources().getColor(R.color.low));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyList extends RecyclerView.ViewHolder{
        TextView tvTitle,tvDate;
        View view_label;
        public MyList(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            view_label = itemView.findViewById(R.id.view_label);
        }
    }
}
