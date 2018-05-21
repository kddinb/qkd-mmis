package com.qkd.entity;

import java.io.Serializable;
import java.util.List;

public class PharmacyForm implements Serializable{

    private static final long serialVersionUID = 1L;

    List<PharmacyMed> pharmacyMedList;

    public List<PharmacyMed> getPharmacyMedList() {
        return pharmacyMedList;
    }

    public void setPharmacyMedList(List<PharmacyMed> pharmacyMedList) {
        this.pharmacyMedList = pharmacyMedList;
    }

    public int getLen(){
        return pharmacyMedList.size();
    }

    public PharmacyMed getPharmacy(int i){
        return pharmacyMedList.get(i);
    }
}