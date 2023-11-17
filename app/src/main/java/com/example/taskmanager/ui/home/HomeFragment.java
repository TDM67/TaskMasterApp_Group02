package com.example.taskmanager.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.activities.MainActivity;
import com.example.taskmanager.adapter.MyListAdapter;
import com.example.taskmanager.databinding.FragmentHomeBinding;
import com.example.taskmanager.helpers.LocalDBHelper;
import com.example.taskmanager.models.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {
    MyListAdapter myListAdapter;
    List<Todo> todoList = new ArrayList<>();
    private FragmentHomeBinding binding;
    TextView tvEmpty;
    Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mContext= container.getContext();
        LocalDBHelper db = new LocalDBHelper(mContext);

        try {
            int cat_id = getArguments().getInt("cat_id", 0);
            if (cat_id != 0) {
                //fetch todos with categories
                todoList = db.getTodosWithId(cat_id);
            } else {
                todoList = db.getAllTodos();
            }
        }catch (Exception exception){
            todoList = db.getAllTodos();
        }

        if(todoList.size() > 0) {
            binding.rvOverview.setVisibility(View.VISIBLE);
            binding.tvEmpty.setVisibility(View.GONE);
            myListAdapter = new MyListAdapter(todoList, getContext());
            binding.rvOverview.setAdapter(myListAdapter);
            binding.rvOverview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        }else{
            binding.rvOverview.setVisibility(View.GONE);
            binding.tvEmpty.setVisibility(View.VISIBLE);
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}