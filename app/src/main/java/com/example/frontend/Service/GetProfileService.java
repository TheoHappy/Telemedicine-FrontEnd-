package com.example.frontend.Service;

import com.example.frontend.Fragment.ScheduleFragment;
import com.example.frontend.Model.Patient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface GetProfileService {
    @GET("/api/Profile/GetProfile")
    Call<Patient> getPatient(@Header("token") String header);
}
