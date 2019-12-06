package com.example.johnyuayan_comp304lab4.Patient;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private LiveData<Integer> intResult;
    private LiveData<Patient> activePatient;
    private MutableLiveData<Integer> livePatientID = new MutableLiveData<>();

    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        intResult = patientRepository.getIntResult();

        // patientRepository.getPatient is called when livePatientID changes
        // Lambda expression to simplify a class that uses the function interface
        activePatient = Transformations.switchMap(livePatientID, id -> patientRepository.getPatient(id));
    }

    //Getters
    public LiveData<Integer> getIntResult() {
        return intResult;
    }
    public LiveData<Patient> getPatient() {
        return activePatient;
    }

    // void Methods
    public void insert(Patient patient) {
        patientRepository.insert(patient);
    }
    public void update(Patient patient) {
        patientRepository.update(patient);
    }
    public void setPatientID(int patientID) {
        livePatientID.setValue(patientID);
    }

}
