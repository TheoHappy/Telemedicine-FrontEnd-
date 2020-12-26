package com.example.frontend.Service;

import com.example.frontend.Model.Patient;
import com.example.frontend.Model.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAuthService {
    @FormUrlEncoded
    @POST("/api/Login/UserAuth")
    Call<ResponseAuth> authPatient(
            @Field("Email") String email,
            @Field("Password") String password
    );
}
