package com.example.frontend.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frontend.Model.Patient;
import com.example.frontend.Model.ResponseAuth;
import com.example.frontend.R;
import com.example.frontend.Service.ServiceBuilder;
import com.example.frontend.Service.UserAuthService;
import com.example.frontend.Service.UserRegService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity {

    private Button btnSignUp;
    private Button btnLogin;

    private EditText etEmailAdress;
    private EditText etPassword;

    public static String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignUp = findViewById(R.id.btn_signUp);
        btnLogin = findViewById(R.id.btn_login);

        etEmailAdress = findViewById(R.id.et_emailAdress);
        etPassword = findViewById(R.id.et_password);

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
                authUser(etEmailAdress.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void authUser(String emailAdress, String password){
        UserAuthService userAuthService = ServiceBuilder.buildService(UserAuthService.class);
        Call<ResponseAuth> request = userAuthService.authPatient(
//                *Values for production*
                emailAdress,
                password
//                *Values for Testing*
//                "andreiTest22@gmail.com",
//              "pass21"
        );
        request.enqueue(new Callback<ResponseAuth>() {
            @Override
            public void onResponse(Call<ResponseAuth> request, Response<ResponseAuth> response) {
                if (response.code() == 200)
                {
                    token = response.body().getMessage();
                    Intent loginIntent = new  Intent(LoginActivity.this, HomeActivity.class);
                    loginIntent.putExtra("token",token);
                    startActivity(loginIntent);
                }else Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseAuth> request, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}