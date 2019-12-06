package com.example.johnyuayan_comp304lab4.Test;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TestDao {
    @Insert
    void insert(Test test);
    @Query("SELECT * FROM Test WHERE patientId == :patientId")
    LiveData<List<Test>> getPatientTests(int patientId);
}
