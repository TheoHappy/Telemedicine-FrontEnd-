package com.example.frontend.Service;

import com.example.frontend.Model.PatientConsultation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AddConsultationService {
    @FormUrlEncoded
    @POST("/api/Doctor/AddConsultation")
    Call<PatientConsultation> addConsultation(@Header("token") String header,
            @Field("Name") String name,
            @Field("Disease") String disease,
            @Field("Address") String address,
            @Field("Description") String description
    );
}
