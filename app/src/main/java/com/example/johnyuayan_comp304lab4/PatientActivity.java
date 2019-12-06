package com.example.johnyuayan_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnyuayan_comp304lab4.Patient.Patient;
import com.example.johnyuayan_comp304lab4.Patient.PatientViewModel;

public class PatientActivity extends AppCompatActivity {

    EditText txtPatientId;
    EditText txtFirstName;
    EditText txtLastName;
    EditText txtDepartment;
    EditText txtRoomNumber;
    TextView lblPatientDisplay;
    Button btnCreatePatient;

    private SharedPreferences myPreference;
    private SharedPreferences.Editor prefEditor;

    private PatientViewModel patientViewModel;
    private int nurseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        // Create the SharedPreference
        myPreference = getSharedPreferences("Login", 0);
        //prepare it for edit by creating and Edit object
        prefEditor = myPreference.edit();

        nurseId = myPreference.getInt("id", -1);

        if(nurseId == -1) {
            startActivity(new Intent(this, LoginActivity.class));
        }


        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        txtPatientId = findViewById(R.id.patientIDText);
        txtFirstName = findViewById(R.id.patientFirstNameText);
        txtLastName = findViewById(R.id.patientLastNameText);
        txtDepartment = findViewById(R.id.departmentText);
        txtRoomNumber = findViewById(R.id.roomText);
        btnCreatePatient = findViewById(R.id.saveButton);
        lblPatientDisplay = findViewById(R.id.patientDisplay);


        //Checks database for patient with patient Id whenever text is changed.
        txtPatientId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not Used
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not Used
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 0) {
                    patientViewModel.setPatientID(Integer.parseInt(editable.toString()));
                }
            }
        });

        btnCreatePatient.setOnClickListener( view ->{
            try {
                Patient patient = new Patient();
                String patientId = txtPatientId.getText().toString();
                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();
                String department = txtDepartment.getText().toString();
                String roomNumber = txtRoomNumber.getText().toString();

                patient.setNurseId(nurseId);
                if(patientId.length() > 0) {
                    patient.setPatientId(Integer.parseInt(patientId));
                }

                if(firstName.length() == 0) {
                    missingField(txtFirstName);
                    return;
                }
                patient.setFirstName(firstName);

                if(lastName.length() == 0) {
                    missingField(txtLastName);
                    return;
                }
                patient.setLastName(lastName);

                if(department.length() == 0) {
                    missingField(txtDepartment);
                    return;
                }
                patient.setDepartment(department);

                if(roomNumber.length() == 0) {
                    missingField(txtRoomNumber);
                    return;
                }
                patient.setRoomNumber(Integer.parseInt(roomNumber));
                patientViewModel.insert(patient);

            } catch (Exception e) {
                Toast.makeText(PatientActivity.this, "Invalid form values", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        //observers

        patientViewModel.getPatient().observe(this, patient -> {
            if(patient != null) {
                lblPatientDisplay.setText(patient.toString());
            }
            else {
                lblPatientDisplay.setText("");
            }
        });

        patientViewModel.getIntResult().observe(this, result -> {
            switch(result) {
                case 1:
                    Toast.makeText(PatientActivity.this, "Patient successfully saved", Toast.LENGTH_SHORT).show();
                    break;
                case -1:
                    Toast.makeText(PatientActivity.this, "Error saving patient", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
    private void missingField(EditText txtField) {
        txtField.requestFocus();
        txtField.setError("Required Field");
    }
}

