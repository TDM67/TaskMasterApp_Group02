package com.example.taskmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.activities.AddToDoActivity;
import com.example.taskmanager.activities.MainActivity;
import com.example.taskmanager.helpers.LocalDBHelper;
import com.example.taskmanager.models.Todo;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyList> {
    List<Todo> list = Collections.emptyList();
    Context mContext;
    
    public MyListAdapter(List<Todo> list, Context context){
        this.list = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new MyListAdapter.MyList(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyList holder, int position) {
        Todo todo = list.get(position);
        holder.tvTitle.setText(todo.getName());
        holder.tvTitle.setMovementMethod(new ScrollingMovementMethod());
        holder.tvTitle.setSingleLine(true);
        holder.tvTitle.setHorizontallyScrolling(true);
        holder.tvTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.tvTitle.setMarqueeRepeatLimit(-1);
        holder.tvTitle.setSelected(true);
        holder.tvTitle.setPadding(10, 0, 10, 0);
        String dateString = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS").format(new Date(todo.getCreated()));
        holder.tvDate.setText(dateString);
        if(todo.getDone())
            holder.done.setVisibility(View.VISIBLE);
        else
            holder.done.setVisibility(View.GONE);
        holder.more.setOnClickListener(view-> openOptionMenu(view, todo));
    }
    public void openOptionMenu(View v, Todo todo){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.item_menu, popup.getMenu());

        if(todo.getDone()){
            popup.getMenu().getItem(0).setVisible(false);
            popup.getMenu().getItem(1).setVisible(false);
        }

        popup.setOnMenuItemClickListener(item -> {
            LocalDBHelper db = new LocalDBHelper(mContext);

            if(item.getTitle().equals("Edit")){
                Intent intent=new Intent(mContext, AddToDoActivity.class);
                intent.putExtra("todo_id",todo.getId());
                mContext.startActivity(intent);
            }
            else if(item.getTitle().equals("Completed")){
                //update status to completed || (done = true)
                todo.setDone(true);

                if(db.updateTodo(todo)) Toast.makeText(mContext, "Updated!", Toast.LENGTH_SHORT).show();
                else Toast.makeText(mContext, "Failed!", Toast.LENGTH_SHORT).show();

            }
            else if(item.getTitle().equals("Delete")){
                //delete the todo using id
                if(db.deleteTodo(todo.getId())) {
                    Toast.makeText(mContext, "deleted!", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                }
                else    Toast.makeText(mContext, "failed!", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
        popup.show();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyList extends RecyclerView.ViewHolder{
        TextView tvTitle,tvDate;
        ImageView more, done;
        public MyList(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            more = itemView.findViewById(R.id.more);
            done = itemView.findViewById(R.id.done);
        }
    }
}
