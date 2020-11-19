package com.example.frontend.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.frontend.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnSignUp;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignUp = findViewById(R.id.btn_signUp);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new  Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new  Intent(LoginActivity.this, HomeActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}