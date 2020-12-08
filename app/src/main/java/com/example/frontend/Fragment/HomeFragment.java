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
import com.example.frontend.R;


public class HomeFragment extends Fragment {

    private RecyclerView rvDoctorList;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    String names[], locations[], speciality[], rating[];
    int images[] = {R.drawable.avatar};
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
        names = getResources().getStringArray(R.array.names);
        locations = getResources().getStringArray(R.array.location);
        speciality = getResources().getStringArray(R.array.speciality);
        rating = getResources().getStringArray(R.array.rating);

        AdapterDoctorList adapterDoctorList = new AdapterDoctorList(getContext(),names,locations,speciality,rating,images);
        rvDoctorList.setAdapter(adapterDoctorList);
        rvDoctorList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}