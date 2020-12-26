package com.example.frontend.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.frontend.Model.Doctor;
import com.example.frontend.Model.PatientConsultation;
import com.example.frontend.R;
import com.example.frontend.Service.AddConsultationService;
import com.example.frontend.Service.GetDoctorService;
import com.example.frontend.Service.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestFragment extends Fragment {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private Button btnRequest;

    private EditText etName;
    private EditText etDesease;
    private EditText etLocation;
    private EditText etDescription;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getView().findViewById(R.id.toolbar);
        toolbarTitle = getView().findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Request");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction().
                        replace(R.id.FL_fragment_container,new HomeFragment()).commit();
            }
        });
        Bundle bundle = getArguments();
        final String token = bundle.getString("token");

        etName = getView().findViewById(R.id.et_name);
        etDescription = getView().findViewById(R.id.et_description);
        etDesease = getView().findViewById(R.id.et_desease);
        etLocation = getView().findViewById(R.id.et_location);

        btnRequest = getView().findViewById(R.id.btn_request);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addConsultation(token,etName.getText().toString(),etDesease.getText().toString(),etLocation.getText().toString(),etDescription.getText().toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        return view;

    }

    public void addConsultation(final String token, String name, String disease, String address, String description){

        AddConsultationService addConsultationService;
        addConsultationService = ServiceBuilder.buildService(AddConsultationService.class);
        Call<PatientConsultation> call = addConsultationService.addConsultation(token,
//                *Values for Testing*
//                "Andrei",
//                "interventie",
//                "Vadul lui voda",
//                "foarte rau"
//                *Values for production*
                name,
                disease,
                address,
                description
                );

        call.enqueue(new Callback<PatientConsultation>() {
            @Override
            public void onResponse(Call<PatientConsultation> request, Response<PatientConsultation> response) {
                System.out.println(response.code());
                if (response.code() == 200) {
                    Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                    ApprovedRequestFragment approvedRequestFragment = new ApprovedRequestFragment();
                    Bundle sendRToApprovedRBundle = new Bundle();
                    sendRToApprovedRBundle.putString("name", response.body().getName());
                    sendRToApprovedRBundle.putString("token", token);
                    sendRToApprovedRBundle.putString("disease", response.body().getDisease());
                    sendRToApprovedRBundle.putString("address", response.body().getAddress());
                    sendRToApprovedRBundle.putString("description", response.body().getDescription());
                    sendRToApprovedRBundle.putString("docId", response.body().getDocId());

                    approvedRequestFragment.setArguments(sendRToApprovedRBundle);
                    ((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.FL_fragment_container,approvedRequestFragment).commit();
                }
            }

            @Override
            public void onFailure(Call<PatientConsultation> request, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
