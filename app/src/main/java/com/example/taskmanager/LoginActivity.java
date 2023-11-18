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

import com.example.taskmanager.activities.MainActivity;
import com.example.taskmanager.helpers.LocalDBHelper;
import com.example.taskmanager.models.User;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText etEmail;
    private EditText etPassword;
    private Button bLogin;
    private ProgressBar pbLogin;
    private TextView tvCreateAccount,tvForgetPass;
    LocalDBHelper db = new LocalDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        pbLogin = findViewById(R.id.pbLogin);
        pbLogin.setVisibility(View.INVISIBLE);
        bLogin = findViewById(R.id.bLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);
        tvForgetPass = findViewById(R.id.tvForgetPass);

        bLogin.setOnClickListener((View v) -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            pbLogin.setVisibility(View.VISIBLE);
            proceedLogin(email,password, v);
        });

        tvCreateAccount.setOnClickListener(view->startActivity(new Intent(this, RegisterActivity.class)));
        tvForgetPass.setOnClickListener(view->startActivity(new Intent(this, ForgetPassActivity.class)));
    }

    private void proceedLogin(String email, String password, View view){
        if(isValidEmailAddress() && isValidPassword()){
            User user =db.getLogin(etEmail.getText().toString(), etPassword.getText().toString());
            if(user.getName()!= null){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }else{
                Snackbar.make(view, "Invalid credentials!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
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