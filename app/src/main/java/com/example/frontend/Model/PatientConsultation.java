package com.example.frontend.Model;

import com.google.gson.annotations.SerializedName;

public class PatientConsultation {
    @SerializedName("ConsId")
    private Integer consId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Disease")
    private String disease;
    @SerializedName("Address")
    private String address;
    @SerializedName("Description")
    private String description;
    @SerializedName("DocId")
    private String docId;
    @SerializedName("IsConfirmed")
    private String isConfirmed;

    public Integer getConsId() {
        return consId;
    }

    public void setConsId(Integer consId) {
        this.consId = consId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(String isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    @Override
    public String toString() {
        return "PatientConsultation{" +
                "consId=" + consId +
                ", name='" + name + '\'' +
                ", disease='" + disease + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", docId='" + docId + '\'' +
                ", isConfirmed='" + isConfirmed + '\'' +
                '}';
    }


}
