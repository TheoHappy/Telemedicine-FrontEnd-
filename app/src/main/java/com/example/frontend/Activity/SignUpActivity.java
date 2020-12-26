package com.example.frontend.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frontend.Base64Handler;
import com.example.frontend.Model.Patient;
import com.example.frontend.R;
import com.example.frontend.Service.ServiceBuilder;
import com.example.frontend.Service.UserRegService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private TextView toolbarTitle;
    private Button btnNext;
    private Button btnAddPhoto;

    private String b64IMG;

    private EditText etFullName;
    private EditText etBirthday;
    private EditText etEmailAddress;
    private EditText etPhone;
    private EditText etLocation;
    private EditText etPassword;

    private static final int IMG_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Register");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnNext = findViewById(R.id.btn_next);
        btnAddPhoto = findViewById(R.id.btn_add_photos);

        etFullName = findViewById(R.id.et_full_name);
        etBirthday = findViewById(R.id.et_birthday);
        etEmailAddress = findViewById(R.id.et_emailAdress);
        etPhone = findViewById(R.id.et_phone);
        etLocation = findViewById(R.id.et_location);
        etPassword = findViewById(R.id.et_password_value);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                registerPatient(etFullName.getText().toString(),
//                        etBirthday.getText().toString(),
//                        etEmailAddress.getText().toString(),
//                        etPhone.getText().toString(),
//                        etLocation.getText().toString(),
//                        "andreiTes12",
//                        etPassword.getText().toString()
//                );
                registerPatient( etFullName.getText().toString(),
                        etBirthday.getText().toString(),
                        etEmailAddress.getText().toString(),
                        etPhone.getText().toString(),
                        etLocation.getText().toString(),
                        etFullName.getText().toString(),
                        etPassword.getText().toString());

            }
        });
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent1, "Select Picture"),
                        IMG_PICK_CODE);
            }
        });


    }

//    private void pickImageFromGallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,IMG_PICK_CODE);
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_CODE: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    pickImageFromGallery();
//                } else {
//                    Toast.makeText(this, "Permission denied ...", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_PICK_CODE) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                Base64Handler base64Handler = new Base64Handler();
                System.out.println("b64- " + base64Handler.bitmapToBase64(bitmap));
                b64IMG = base64Handler.encodeToBase64(bitmap, Bitmap.CompressFormat.PNG, 100);
            } catch (IOException e) {
                Log.i("TAG", "SignUpScreen encode to base64 problem" + e);
            }
        }

    }

    private void registerPatient(String fullName, String birthday, String emailAddress, String phone, String location, String username, String password) {
        UserRegService userRegService = ServiceBuilder.buildService(UserRegService.class);
        Call<String> request = userRegService.registerPatient(
//                Test Values
//                "Dumitru Petrov",
//                "11/8/2017",
//                "andreiTest36@gmail.com",
//                "079862342",
////                "Otovasca",
//                "andreiTest7",
//                "pass21",
//                password,
//                Production

                fullName,
                birthday,
                emailAddress,
                phone,
                location,
                username,
                password,
                "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");

        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> request, Response<String> response) {
                String responseCode = "Response code: " + response.code();
                Toast.makeText(getApplicationContext(), responseCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> request, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}