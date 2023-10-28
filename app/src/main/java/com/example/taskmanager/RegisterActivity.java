package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanager.activities.MainActivity;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText etEmail;
    private EditText etPassword;
    private Button bRegister;
    private ProgressBar pbLogin;
    TextView tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        pbLogin = findViewById(R.id.pbLogin);
        tvLogin = findViewById(R.id.tvLogin);
        pbLogin.setVisibility(View.INVISIBLE);
        tvLogin = findViewById(R.id.tvLogin);
        bRegister = findViewById(R.id.bRegister);

        tvLogin.setOnClickListener(view->startActivity(new Intent(this, LoginActivity.class)));
        bRegister.setOnClickListener((View v) -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            pbLogin.setVisibility(View.VISIBLE);
            proceedRegister(email,password, v);
        });
    }

    private void proceedRegister(String email, String password, View view){
        if(isValidEmailAddress() && isValidPassword()){
            if(email.equals("user@gmail.com") && password.equals("123456"))
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            else
                Snackbar.make(view, "Invalid credentials!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            pbLogin.setVisibility(View.GONE);
        }
    }

    private boolean isValidEmailAddress() {
        String email = etEmail.getText().toString();
        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setFocusable(true);
            etEmail.setError("Invalid email!");
            return false;
        }
        pbLogin.setVisibility(View.GONE);
        return true;
    }

    private boolean isValidPassword() {
        String password = etPassword.getText().toString();
        if(password.isEmpty() || password.length() < 6){
            etPassword.setFocusable(true);
            etPassword.setError("Invalid password!");
            return false;
        }
        pbLogin.setVisibility(View.GONE);
        return true;
    }
}