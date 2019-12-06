package com.example.johnyuayan_comp304lab4.Test;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.johnyuayan_comp304lab4.Nurse.Nurse;
import com.example.johnyuayan_comp304lab4.Patient.Patient;

// POJO Entity class for the Test Table
@Entity(foreignKeys = {@ForeignKey(entity = Nurse.class, parentColumns = "nurseId", childColumns = "nurseId"),
        @ForeignKey(entity = Patient.class, parentColumns = "patientId", childColumns = "patientId")})
public class Test {
    // Private variables
    @PrimaryKey(autoGenerate = true)
    private int testId;
    @NonNull
    private int patientId;
    @NonNull
    private int nurseId;
    private String bloodType;
    private String BPL;
    private String BPH;
    private float temperature;

    @NonNull
    @Override
    public String toString() {
        return "Test Id: " + getTestId() + "\n" +
                "Patient Id: " + getPatientId() + "\n" +
                "Nurse Id: " + getNurseId() + "\n" +
                "Blood Type: " + getBloodType() + "\n" +
                "BPL: " + getBPL() + "\n" +
                "BPH: " + getBPH() + "\n" +
                "Temperature: " + getTemperature() + "\n";
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getBPL() {
        return BPL;
    }

    public void setBPL(String BPL) {
        this.BPL = BPL;
    }

    public String getBPH() {
        return BPH;
    }

    public void setBPH(String BPH) {
        this.BPH = BPH;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
