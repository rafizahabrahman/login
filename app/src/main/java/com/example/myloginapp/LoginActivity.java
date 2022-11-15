package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginButton;
    TextView register;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.btnLogin);
        register = findViewById(R.id.tvSignUp);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.checkUser(email.getText().toString().trim(), password.getText().toString().trim())) {
                    Intent accountsIntent = new Intent(LoginActivity.this, MainActivity.class);
                    accountsIntent.putExtra("EMAIL", email.getText().toString().trim());
                    emptyInputEditText();
                    startActivity(accountsIntent);
                }else{
                    Toast.makeText(LoginActivity.this, "Email or password is incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        email.setText(null);
        password.setText(null);
    }


}













