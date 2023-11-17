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
import com.example.taskmanager.models.Todo;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MyCategoriesAdapter extends RecyclerView.Adapter<MyCategoriesAdapter.MyList> {
    List<Category> list = Collections.emptyList();
    Context mContext;
    public MyCategoriesAdapter(List<Category> list, Context context){
        this.list = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new MyCategoriesAdapter.MyList(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyList holder, int position) {
        Category category = list.get(position);
        holder.tvTitle.setText(category.getName());
        holder.tvCount.setText(category.getCount()+"");
        holder.layout.setBackgroundColor(Color.parseColor(category.getColor()));

        holder.more.setOnClickListener(view-> openOptionMenu(view));
    }
    public void openOptionMenu(View v){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.cat_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            Toast.makeText(mContext, "id:"+item.getItemId()+" You selected the action : " + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        });
        popup.show();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyList extends RecyclerView.ViewHolder{
        TextView tvTitle,tvCount;
        ImageView more;
        LinearLayout layout;
        public MyList(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCount = itemView.findViewById(R.id.tvCount);
            more = itemView.findViewById(R.id.more);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
