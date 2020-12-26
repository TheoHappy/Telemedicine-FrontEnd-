package com.example.frontend.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.frontend.Model.Doctor;
import com.example.frontend.R;
import com.example.frontend.Service.GetDoctorService;
import com.example.frontend.Service.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedRequestFragment extends Fragment {


    private Toolbar toolbar;
    private TextView toolbarTitle;

    private TextView tvName;
    private TextView tvDisease;
    private TextView tvLocation;
    private TextView tvDescription;

    private TextView tvDoctorName;
    private TextView tvSpeciality;
    private TextView tvRating;

    private String docId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getView().findViewById(R.id.toolbar);
        toolbarTitle = getView().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Request");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.FL_fragment_container, new HomeFragment()).commit();
            }
        });

        String token = getArguments().getString("token");

        tvName = getView().findViewById(R.id.tv_name_value);
        tvDisease = getView().findViewById(R.id.tv_desease_value);
        tvLocation = getView().findViewById(R.id.tv_location_value);
        tvDescription = getView().findViewById(R.id.tv_description_value);

        tvDoctorName = getView().findViewById(R.id.tv_name_doctor);
        tvSpeciality = getView().findViewById(R.id.tv_speciality);
        tvRating = getView().findViewById(R.id.tv_rating);


        tvName.setText(getArguments().getString("name"));
        tvDisease.setText(getArguments().getString("disease"));
        tvLocation.setText(getArguments().getString("address"));
        tvDescription.setText(getArguments().getString("description"));

        docId = getArguments().getString("docId");
        getDoctor(token, Integer.parseInt(docId));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_approved_request, container, false);
    }

    public void getDoctor(final String token, int docId) {

        GetDoctorService getDoctorService;
        getDoctorService = ServiceBuilder.buildService(GetDoctorService.class);
        Call<Doctor> call = getDoctorService.getDoctor(token, docId);

        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> request, Response<Doctor> response) {
                Doctor doctor = response.body();
                tvDoctorName.setText(doctor.getFullName());
                tvSpeciality.setText(doctor.getSpecs());
                tvRating.setText(String.valueOf(doctor.getStars()));
            }

            @Override
            public void onFailure(Call<Doctor> request, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}