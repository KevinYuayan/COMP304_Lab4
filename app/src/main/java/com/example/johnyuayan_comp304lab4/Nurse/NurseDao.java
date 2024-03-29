package com.example.johnyuayan_comp304lab4.Nurse;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

// Data Access Object for the Nurse class
// Provides SQL CRUD and queries
@Dao
public interface NurseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(Nurse nurse);
    @Query("SELECT * FROM Nurse WHERE nurseId == :user AND password == :password LIMIT 1")
    Nurse Login(int user, String password);
}

