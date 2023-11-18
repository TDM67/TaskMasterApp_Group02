package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanager.activities.MainActivity;
import com.example.taskmanager.helpers.LocalDBHelper;
import com.example.taskmanager.models.User;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText etEmail;
    private EditText etPassword, etRegister;
    private Button bRegister;
    private ProgressBar pbLogin;
    CheckBox checkbox;
    TextView tvLogin;
    LocalDBHelper db = new LocalDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etRegister = findViewById(R.id.etRegister);
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        pbLogin = findViewById(R.id.pbLogin);
        tvLogin = findViewById(R.id.tvLogin);
          pbLogin.setVisibility(View.INVISIBLE);
        tvLogin = findViewById(R.id.tvLogin);
        checkbox = findViewById(R.id.checkbox);
        bRegister = findViewById(R.id.bRegister);
        checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(!isChecked){
                checkbox.setText("Show password");
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else{
                checkbox.setText("Hide password");
                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });
        tvLogin.setOnClickListener(view->startActivity(new Intent(this, LoginActivity.class)));
        bRegister.setOnClickListener((View v) -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            pbLogin.setVisibility(View.VISIBLE);
            proceedRegister(email,password, v);
        });
    }
    private void proceedRegister(String email, String password, View view){
        if(isValidEmailAddress() && isValidPassword() && isValidUserName()){

            User user = new User();
            user.setName(etRegister.getText().toString());
            user.setEmail(etEmail.getText().toString());
            user.setPassword(etPassword.getText().toString());
            user.setCreated(System.currentTimeMillis());
            boolean insertUser = db.insertUser(user);
            if(insertUser){
                Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
            else
                Snackbar.make(view, "Failed!", Snackbar.LENGTH_SHORT)
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
    private boolean isValidUserName() {
        String name = etRegister.getText().toString();
        if(name.isEmpty()){
            etRegister.setFocusable(true);
            etRegister.setError("Invalid user name!");
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