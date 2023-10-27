package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ForgetPassActivity extends AppCompatActivity {
    private static final String TAG = ForgetPassActivity.class.getSimpleName();
    private EditText etEmail;
    private Button bForgetPass;
    private ProgressBar pbLogin;
    TextView tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        etEmail = findViewById(R.id.etLoginEmail);
        tvLogin = findViewById(R.id.tvLogin);
        bForgetPass = findViewById(R.id.bForget);
        pbLogin = findViewById(R.id.pbLogin);
        pbLogin.setVisibility(View.GONE);
        tvLogin.setOnClickListener(view->startActivity(new Intent(this, LoginActivity.class)));

        bForgetPass.setOnClickListener(view->{
            pbLogin.setVisibility(View.VISIBLE);
            if(isValidEmailAddress())
                Toast.makeText(this, "resent link sent success!", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isValidEmailAddress() {
        String email = etEmail.getText().toString();
        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setFocusable(true);
            etEmail.setError("Invalid email!");
            pbLogin.setVisibility(View.GONE);
            return false;
        }
        return true;
    }
}