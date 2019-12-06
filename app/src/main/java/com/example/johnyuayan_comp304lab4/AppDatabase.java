package com.example.johnyuayan_comp304lab4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.johnyuayan_comp304lab4.Nurse.Nurse;
import com.example.johnyuayan_comp304lab4.Nurse.NurseDao;
import com.example.johnyuayan_comp304lab4.Patient.Patient;
import com.example.johnyuayan_comp304lab4.Patient.PatientDao;
import com.example.johnyuayan_comp304lab4.Test.Test;
import com.example.johnyuayan_comp304lab4.Test.TestDao;

// Abstract class to represent the Database in Room API (
@Database(entities = {Nurse.class, Patient.class, Test.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NurseDao nurseDao();
    public abstract TestDao testDao();
    public abstract PatientDao patientDao();

    private static AppDatabase appDatabaseInstance;
    private static final String APP_DB_NAME = "app_db";

    public static AppDatabase getInstance(Context context) {
        if(appDatabaseInstance == null) {
            appDatabaseInstance = Room.databaseBuilder(context, AppDatabase.class, APP_DB_NAME).build();
        }
        return appDatabaseInstance;
    }
}