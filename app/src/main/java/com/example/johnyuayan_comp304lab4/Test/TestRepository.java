package com.example.johnyuayan_comp304lab4.Test;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.johnyuayan_comp304lab4.AppDatabase;

import java.util.List;

// Manages data sources. Interacts with the Database for the Test Entity/table
public class TestRepository {
    private final TestDao testDao;
    // -2 Error in setPatientTests
    // -1 Error in insert
    // 1 insert success
    // 2 setPatientTests success
    private MutableLiveData<Integer> boolResult = new MutableLiveData<>();

    public TestRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        testDao = db.testDao();
    }
    // Getters
    public LiveData<Integer> getIntResult() { return boolResult; }
    public LiveData<List<Test>> getPatientTests(int patientID) {
        return testDao.getPatientTests(patientID);
    }

    // public methods for db operations
    public void insert(Test test) {
        insertAsync(test);
    }

    // Asynchronous private methods for db operations
    private void insertAsync(final Test test) {
        new Thread(() ->{
            try {
                testDao.insert(test);
                boolResult.postValue(1);
            } catch (Exception e) {
                boolResult.postValue(-1);
            }
        }).start();
    }

}

