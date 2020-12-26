package com.example.frontend.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.frontend.Model.Patient;
import com.example.frontend.R;
import com.example.frontend.Service.GetProfileService;
import com.example.frontend.Service.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduleFragment extends Fragment {

    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getProfile();
    }

//    nu se foloseste nici unde
    public void getProfile(){
            String token = this.getArguments().getString("token");

            GetProfileService taskService;
            taskService = ServiceBuilder.buildService(GetProfileService.class);
            Call<Patient> call = taskService.getPatient(token);

            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> request, Response<Patient> response) {
                    System.out.println("test: " +response.body().toString());
                }

                @Override
                public void onFailure(Call<Patient> request, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
    }

}