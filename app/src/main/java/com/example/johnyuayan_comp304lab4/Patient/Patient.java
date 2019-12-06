package com.example.johnyuayan_comp304lab4.Patient;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.johnyuayan_comp304lab4.Nurse.Nurse;

// POJO Entity class for the Patient Table
@Entity(foreignKeys = @ForeignKey(entity = Nurse.class, parentColumns = "nurseId", childColumns = "nurseId"))
public class Patient {

    // Private variables
    @PrimaryKey(autoGenerate = true)
    private int patientId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String department;
    @NonNull
    private int nurseId;
    @NonNull
    private int roomNumber;

    @NonNull
    @Override
    public String toString() {
        return "Patient Id: " + getPatientId() + "\n" +
                "First Name: " + getFirstName() + "\n" +
                "Last Name: " + getLastName() + "\n" +
                "Department: " + getDepartment() + "\n" +
                "Nurse Id: " + getNurseId() + "\n" +
                "Room #: " + getRoomNumber();
    }
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getDepartment() {
        return department;
    }

    public void setDepartment(@NonNull String department) {
        this.department = department;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
