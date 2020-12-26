package com.example.frontend.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.frontend.Adaptors.AdapterDoctorList;
import com.example.frontend.Model.Doctor;
import com.example.frontend.Model.Patient;
import com.example.frontend.R;
import com.example.frontend.Service.GetDoctorListService;
import com.example.frontend.Service.GetProfileService;
import com.example.frontend.Service.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private RecyclerView rvDoctorList;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getView().findViewById(R.id.toolbar);
        toolbarTitle = getView().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Doctor List");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvDoctorList = getView().findViewById(R.id.rv_doctor_list);
        rvDoctorList.setHasFixedSize(true);


        getDoctorList();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    public void getDoctorList(){

        Bundle bundle = getArguments();
        final String token = bundle.getString("token");
        GetDoctorListService getDoctorListService;
        getDoctorListService = ServiceBuilder.buildService(GetDoctorListService.class);
        Call<List<Doctor>> call = getDoctorListService.getDoctors(token);

        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> request, Response<List<Doctor>> response) {

                List<Doctor> doctors = response.body();
                for (Doctor doctor: doctors){
                    doctor.setToken(token);
                }
                AdapterDoctorList adapterDoctorList = new AdapterDoctorList(((AppCompatActivity)getActivity()),doctors);
                rvDoctorList.setAdapter(adapterDoctorList);
                rvDoctorList.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Doctor>> request, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}