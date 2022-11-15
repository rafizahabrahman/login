package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password, confirmPassword;
    Button registerButton;
    TextView login;
    DatabaseHelper databaseHelper;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);
        registerButton = findViewById(R.id.btnRegister);
        login = findViewById(R.id.tvLogin);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.checkUser(email.getText().toString().trim(), password.getText().toString().trim())) {
                    user.setUsername(username.getText().toString().trim());
                    user.setEmail(email.getText().toString().trim());
                    user.setPassword(password.getText().toString().trim());
                    databaseHelper.addUser(user);
                    //emptyInputEditText();
                }else{
                    Toast.makeText(RegisterActivity.this, "Email or password is incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }






}