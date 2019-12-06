package com.example.johnyuayan_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnyuayan_comp304lab4.Patient.PatientViewModel;
import com.example.johnyuayan_comp304lab4.Test.Test;
import com.example.johnyuayan_comp304lab4.Test.TestViewModel;

public class TestActivity extends AppCompatActivity {

    EditText txtTestId;
    EditText txtPatientId;
    EditText txtBloodType;
    EditText txtBPL;
    EditText txtBPH;
    EditText txtTemperature;
    Button btnCreateTest;


    private SharedPreferences myPreference;
    private SharedPreferences.Editor prefEditor;

    private Test test;

    private TestViewModel testViewModel;
    private PatientViewModel patientViewModel;
    private int nurseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        // Create the SharedPreference
        myPreference = getSharedPreferences("Login", 0);
        //prepare it for edit by creating and Edit object
        prefEditor = myPreference.edit();

        nurseId = myPreference.getInt("id", -1);

        if(nurseId == -1) {
            startActivity(new Intent(this, LoginActivity.class));
        }


        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        txtTestId = findViewById(R.id.testIDText);
        txtPatientId = findViewById(R.id.patientIDText);
        txtBloodType = findViewById(R.id.bloodTypeText);
        txtBPL = findViewById(R.id.BPLText);
        txtBPH = findViewById(R.id.BPHText);
        txtTemperature = findViewById(R.id.temperatureText);
        btnCreateTest = findViewById(R.id.saveTestBtn);

        btnCreateTest.setOnClickListener( view ->{
            try {
                // Test allows nullables, except for IDs
                test = new Test();
                String testId = txtTestId.getText().toString();
                String patientId = txtPatientId.getText().toString();
                String bloodType = txtBloodType.getText().toString();
                String BPL = txtBPL.getText().toString();
                String BPH = txtBPH.getText().toString();
                String temperature = txtTemperature.getText().toString();

                test.setNurseId(TestActivity.this.nurseId);
                if(testId.length() > 0) {
                    test.setTestId(Integer.parseInt(testId));
                }

                if(bloodType.length() > 0) {
                    test.setBloodType(bloodType);
                }

                if(BPL.length() > 0) {
                    test.setBPL(BPL);
                }
                if(BPH.length() > 0) {
                    test.setBPH(BPH);
                }
                if(temperature.length() > 0) {
                    try {
                        test.setTemperature(Float.parseFloat(temperature));
                    }catch (Exception e) {
                        txtTemperature.requestFocus();
                        txtTemperature.setError("Invalid Temperature");
                        return;
                    }
                }

                // Called last so we can check db if patient exists
                if(patientId.length() == 0) {
                    txtPatientId.requestFocus();
                    txtPatientId.setError("Required Field");
                    return;
                }
                //go to patientViewModel.getPatient().observe for continuation
                patientViewModel.setPatientID(Integer.parseInt(patientId));
            } catch (Exception e) {
                Toast.makeText(TestActivity.this, "Invalid form values", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        // observers

        patientViewModel.getPatient().observe(this, patient -> {
            if(patient != null) {
                test.setPatientId(patient.getPatientId());
                testViewModel.insert(test);
            }
            else {
                txtPatientId.requestFocus();
                txtPatientId.setError("Invalid PatientID");
            }
        });

        testViewModel.getIntResult().observe(this, result -> {
            //Test successfully added
            if (result == 1) {
                Toast.makeText(TestActivity.this, "Test successfully saved", Toast.LENGTH_SHORT).show();
            } else {
                //Error inserting Test. Probably already exists
                Toast.makeText(TestActivity.this, "Test ID already exists", Toast.LENGTH_SHORT).show();
            }
        });
    }
}