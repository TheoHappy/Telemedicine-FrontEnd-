package com.example.frontend.Fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.frontend.Model.Doctor;
import com.example.frontend.R;
import com.example.frontend.Service.GetDoctorListService;
import com.example.frontend.Service.GetDoctorService;
import com.example.frontend.Service.ServiceBuilder;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment implements OnMapReadyCallback {
    private FragmentActivity myContext;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private String token;
    String docId;
    String location;

    private TextView tvDoctorName;
    private TextView tvSpeciality;
    private TextView tvRating;
    private TextView tvLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getView().findViewById(R.id.toolbar);
        toolbarTitle = getView().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Doctor Details");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.FL_fragment_container, new HomeFragment()).commit();
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tvDoctorName = getView().findViewById(R.id.tv_name_doctor);
        tvSpeciality = getView().findViewById(R.id.tv_speciality);
        tvRating = getView().findViewById(R.id.tv_rating);
        tvLocation = getView().findViewById(R.id.tv_location_value);

        Bundle bundle = getArguments();
        docId = bundle.getString("docId");
        token = bundle.getString("token");
        System.out.println("tk- " + token);
        System.out.println("dk:" +docId);
        if (docId != null) {
            getDoctor(token, Integer.parseInt(docId));
        }
//        Bundle bundle = getArguments();
//        docId = bundle.getString("docId");
//        token = bundle.getString("token");
//        getDoctor(token,Integer.parseInt(docId));

    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        float zoomLevel = 16.0f;
        LatLng latLng = new LatLng(47.005155, 28.89372);

        MarkerOptions marker = new MarkerOptions().position(latLng)
                .title("UTM");
        googleMap.addMarker(marker);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
    }


    public void getDoctor(final String token, int docId) {

        GetDoctorService getDoctorService;
        getDoctorService = ServiceBuilder.buildService(GetDoctorService.class);
        Call<Doctor> call = getDoctorService.getDoctor(token, docId);

        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> request, Response<Doctor> response) {
                if (response.code() == 200) {
                    Doctor doctor = response.body();
                    location = doctor.getAddress();
                    tvDoctorName.setText(doctor.getFullName());
                    tvSpeciality.setText(doctor.getSpecs());
                    tvRating.setText(String.valueOf(doctor.getStars()));
                    tvLocation.setText(response.body().getAddress());
                }

            }

            @Override
            public void onFailure(Call<Doctor> request, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
