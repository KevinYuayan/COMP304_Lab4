package com.example.johnyuayan_comp304lab4.Nurse;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// POJO Entity class for the Nurse Table
@Entity
public class Nurse {
    // Private variables
    @PrimaryKey(autoGenerate = true)
    private int nurseId;
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
