package com.example.johnyuayan_comp304lab4.Patient;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

// import java.util.List;

// Data Access Object for the Patient class
// Provides SQL CRUD and queries
@Dao
public interface PatientDao {
    @Insert
    void insert(Patient patient);
    @Update
    void update(Patient patient);

    @Query("select * from Patient where patientId == :patientId")
    LiveData<Patient> getPatient(int patientId);

    // Used for debugging
//    @Query("select * from Patient")
//    LiveData<List<Patient>> getPatientList();
}
