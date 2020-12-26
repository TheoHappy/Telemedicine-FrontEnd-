package com.example.frontend.Adaptors;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.Fragment.ProfileFragment;
import com.example.frontend.Model.Doctor;
import com.example.frontend.R;

import java.util.List;

public class AdapterDoctorList  extends RecyclerView.Adapter<AdapterDoctorList.DoctorViewHolder> {


    private List<Doctor> doctorList;
    Context context;

    public AdapterDoctorList (Context context, List<Doctor> doctorList){
        this.context = context;
        this.doctorList = doctorList;
    }


    @Override
    public DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_row,parent,false);
        DoctorViewHolder doctorViewHolder = new DoctorViewHolder(v);
        return doctorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        final Doctor currentDoctor = doctorList.get(position);

        holder.tvName.setText(currentDoctor.getFullName());
        holder.tvSpeciality.setText(currentDoctor.getSpecs());
        holder.tvLocation.setText(currentDoctor.getAddress());
        holder.tvRating.setText(String.valueOf(currentDoctor.getStars()));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment profileFragment = new ProfileFragment();
                Bundle homeFToProfileFBundle = new Bundle();
                homeFToProfileFBundle.putString("docId",String.valueOf(currentDoctor.getDocId()));
                homeFToProfileFBundle.putString("token",currentDoctor.getToken());
                profileFragment.setArguments(homeFToProfileFBundle);
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.FL_fragment_container,profileFragment).commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvSpeciality;
        TextView tvLocation;
        TextView tvRating;
        ImageView ivAvatar;
        ConstraintLayout mainLayout;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSpeciality = itemView.findViewById(R.id.tv_speciality);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvRating = itemView.findViewById(R.id.tv_rating);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            mainLayout = itemView.findViewById(R.id.ConstraintLayoutCard);
        }
    }
}
