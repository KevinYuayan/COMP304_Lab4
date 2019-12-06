package com.example.johnyuayan_comp304lab4.Patient;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.johnyuayan_comp304lab4.AppDatabase;

// Manages data sources. Interacts with the Database for the Test Entity/table
public class PatientRepository {
    private final PatientDao patientDao;
    // -2 = error in setPatient
    // -1 = error insert/update
    // 1 = insert success
    // 2 = update success
    // 3 = setPatient success
    private MutableLiveData<Integer> intResult = new MutableLiveData<>();

    public PatientRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        patientDao = db.patientDao();
    }
    // Getters
    public LiveData<Integer> getIntResult() { return intResult; }

    // public methods for db operations
    public void insert(Patient patient) {
        insertAsync(patient);
    }
    public void update(Patient patient) {
        updateAsync(patient);
    }

    // Asynchronous private methods for db operations
    private void insertAsync(final Patient patient) {
        new Thread( () -> {
            try {
                patientDao.insert(patient);
                intResult.postValue(1);
            } catch (Exception e) {
                intResult.postValue(-1);
            }
        }).start();
    }
    private void updateAsync(final Patient patient) {
        new Thread(()-> {
            try {
                if(patientDao.getPatient(patient.getPatientId()) == null) {
                    throw new Exception();
                }
                patientDao.update(patient);
                intResult.postValue(2);
            } catch (Exception e) {
                intResult.postValue(-1);
            }
        }).start();
    }

    // Async not needed
    // Only called by Transformations.SwitchMap function
    public LiveData<Patient> getPatient(final int patientId) {
        LiveData<Patient> patient = patientDao.getPatient(patientId);
        return patient;
    }
}

