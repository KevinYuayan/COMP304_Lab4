package com.example.johnyuayan_comp304lab4.Test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;


public class TestViewModel extends AndroidViewModel {
    private TestRepository testRepository;
    private LiveData<Integer> intResult;
    private MutableLiveData<Integer> livePatientID = new MutableLiveData<>();
    private LiveData<List<Test>> patientTests;


    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        intResult = testRepository.getIntResult();
        // testRepository.getPatientTests is called when livePatientID changes
        // Lambda expression to simplify a class that uses the "function<>" interface
        patientTests = Transformations.switchMap(livePatientID, patientID -> testRepository.getPatientTests(patientID));
    }

    // Getters
    public LiveData<Integer> getIntResult() {
        return intResult;
    }
    public LiveData<List<Test>> getPatientTests() {
        return patientTests;
    }

    //void Methods
    public void setPatientID(int patientID) {
        livePatientID.setValue(patientID);
    }
    public void insert(Test test) {
        testRepository.insert(test);
    }

}
