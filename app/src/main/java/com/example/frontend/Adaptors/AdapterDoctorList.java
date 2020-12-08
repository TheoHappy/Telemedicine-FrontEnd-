package com.example.frontend.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.R;

public class AdapterDoctorList  extends RecyclerView.Adapter<AdapterDoctorList.DoctorViewHolder> {
    String names[], locations[], speciality[], rating[];
    int images[] ;
    Context context;

    public AdapterDoctorList (Context ct, String names[],String location[], String speciality[], String rating[], int images[] ){
        context = ct;
        this.names = names;
        this.locations = location;
        this.speciality = speciality;
        this.images = images;
        this.rating = rating;

    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.doctor_row,parent,false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        holder.tvName.setText(names[position]);
        holder.tvSpeciality.setText(speciality[position]);
        holder.tvLocation.setText(locations[position]);
        holder.tvRating.setText(rating[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvSpeciality;
        TextView tvLocation;
        TextView tvRating;
        ImageView ivAvatar;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSpeciality = itemView.findViewById(R.id.tv_speciality);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvRating = itemView.findViewById(R.id.tv_rating);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
