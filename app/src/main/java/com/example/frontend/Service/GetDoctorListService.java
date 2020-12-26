package com.example.frontend.Service;

import com.example.frontend.Model.Doctor;
import com.example.frontend.Model.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GetDoctorListService {
    @GET("/api/Doctor/GetDoctorList")
    Call<List<Doctor>> getDoctors(@Header("token") String header);
}
