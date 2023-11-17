package com.example.taskmanager.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.R;
import com.example.taskmanager.adapter.MyCategoriesAdapter;
import com.example.taskmanager.helpers.LocalDBHelper;
import com.example.taskmanager.models.Category;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity  {
    RecyclerView categoryRecycle;
    TextView tvEmpty;
    MyCategoriesAdapter myCategoriesAdapter;
    FloatingActionButton fabAdd;
    Category category;
    List<Category> categoryList=new ArrayList<>();
    LocalDBHelper db = new LocalDBHelper(this);

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);
        categoryRecycle = findViewById(R.id.categoryRecycle);
        fabAdd = findViewById(R.id.fabAdd);
        tvEmpty = findViewById(R.id.tvEmpty);
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryList = db.getAllCategories();
        if(categoryList.size() != 0) {
            tvEmpty.setVisibility(View.GONE);
            categoryRecycle.setVisibility(View.VISIBLE);
            myCategoriesAdapter = new MyCategoriesAdapter(categoryList, this);
            categoryRecycle.setAdapter(myCategoriesAdapter);
            categoryRecycle.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }else{
            tvEmpty.setVisibility(View.VISIBLE);
            categoryRecycle.setVisibility(View.GONE);
        }
        fabAdd.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.add_category);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            Button btAdd = dialog.findViewById(R.id.btAdd);
            TextView close_text = dialog.findViewById(R.id.close_text);
            EditText etCategoryName = dialog.findViewById(R.id.etCategoryName);
            dialog.show();

            close_text.setOnClickListener(view-> dialog.dismiss());

            btAdd.setOnClickListener(view->{
                if(etCategoryName.getText().toString().isEmpty()){
                    etCategoryName.setFocusable(true);
                    etCategoryName.setError("Invalid!");
                }
                else{
                    category = new Category();
//                    //store category
                    category.setName(etCategoryName.getText().toString());
                    category.setCount(0);
                    category.setColor("#4287f5");
                    boolean dbInsertionSucceeded = db.insertCategory(category);

                    if(dbInsertionSucceeded)
                        Toast.makeText(this, "Added!", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                    startActivity(new Intent(this, CategoriesActivity.class));
                }
            });
        });

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