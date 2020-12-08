package com.example.frontend.Service;

import com.example.frontend.Model.Patient;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserRegService {
//    @POST("/api/Register/UserReg")
//    Call<Patient> registerPatient(@Body Patient patient);

    @FormUrlEncoded
    @POST("/api/Register/UserReg")
    Call<Patient> registerPatient(
            @Field("FullName") String fullName,
            @Field("Birthday") Date birthday,
            @Field("Email") String email,
            @Field("Phone") String phone,
            @Field("Address") String address,
            @Field("Username") String username,
            @Field("Password") String password,
            @Field("Base64Photo") String base64Photo
    );
}
