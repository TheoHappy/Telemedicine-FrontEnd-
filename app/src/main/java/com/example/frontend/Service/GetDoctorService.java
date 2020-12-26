package com.example.frontend.Service;

import com.example.frontend.Model.Doctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GetDoctorService {
    @GET("/api/Doctor/GetDoctor/{id}")
    Call<Doctor> getDoctor(@Header("token") String header, @Path("id") int docId);
}
