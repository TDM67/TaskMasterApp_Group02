package com.example.taskmanager.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.adapter.MyNotificationsAdapter;
import com.example.taskmanager.models.Notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity  {
    RecyclerView notifRecycle;
    MyNotificationsAdapter myNotificationsAdapter;
    List<Notification> notificationList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);
        notifRecycle = findViewById(R.id.notifRecycle);
        getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        long millis = new Date().getTime();

        notificationList.add(new Notification(1,"Call technician for fridge repair", "","#000123",millis,2));
        notificationList.add(new Notification(2,"Pickup Alex from Airport", "","#000123",millis,1));
        notificationList.add(new Notification(3,"Read Mount High book", "","#000123",millis,3));

        myNotificationsAdapter = new MyNotificationsAdapter(notificationList, this);
        notifRecycle.setAdapter(myNotificationsAdapter);
        notifRecycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}