package com.example.taskmanager.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.adapter.MyListAdapter;
import com.example.taskmanager.databinding.FragmentHomeBinding;
import com.example.taskmanager.models.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {
    MyListAdapter myListAdapter;
    List<Todo> todoList = new ArrayList<>();
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        long millis = new Date().getTime();

        todoList.add(new Todo(1,"Todo 1","wake up early","Fav", millis,millis + 72000,true,true));
        todoList.add(new Todo(2,"Todo 2","goto airport and pickup James","Fav", millis,millis + 72000,true,true));
        todoList.add(new Todo(3,"Todo 3","read social science","Fav", millis,millis + 72000,true,true));
        todoList.add(new Todo(4,"Todo 4","dinner with Alix","Fav", millis,millis + 72000,true,true));
        myListAdapter = new MyListAdapter(todoList, getContext());
        binding.rvOverview.setAdapter(myListAdapter);
        binding.rvOverview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}