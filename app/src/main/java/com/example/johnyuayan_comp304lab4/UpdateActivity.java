package com.example.johnyuayan_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnyuayan_comp304lab4.Patient.Patient;
import com.example.johnyuayan_comp304lab4.Patient.PatientViewModel;

public class UpdateActivity extends AppCompatActivity {

    EditText txtPatientId;
    EditText txtFirstName;
    EditText txtLastName;
    EditText txtDepartment;
    EditText txtRoomNumber;
    Button btnUpdatePatient;

    private Patient newPatient;
    private Patient livePatient;
    private SharedPreferences myPreference;
    private SharedPreferences.Editor prefEditor;

    private PatientViewModel patientViewModel;
    private int nurseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Create the SharedPreference
        myPreference = getSharedPreferences("Login", 0);
        //prepare it for edit by creating and Edit object
        prefEditor = myPreference.edit();

        nurseId = myPreference.getInt("id", -1);

        if(nurseId == -1) {
            startActivity(new Intent(this, LoginActivity.class));
        }


        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        txtPatientId = findViewById(R.id.patientIDUpdateText);
        txtFirstName = findViewById(R.id.firstNameUpdateText);
        txtLastName = findViewById(R.id.lastNameEditText);
        txtDepartment = findViewById(R.id.departmentTextUpdate);
        txtRoomNumber = findViewById(R.id.roomTextEdit);
        btnUpdatePatient = findViewById(R.id.updateButton);


        //Checks database for Patient with Patient Id whenever text is changed.
        txtPatientId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() > 0) {
                    patientViewModel.setPatientID(Integer.parseInt(editable.toString()));
                }
            }
        });



        btnUpdatePatient.setOnClickListener( view ->{
            try {
                newPatient = new Patient();
                String patientId = txtPatientId.getText().toString();
                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();
                String department = txtDepartment.getText().toString();
                String roomNumber = txtRoomNumber.getText().toString();


                // livePatient is set in the observer
                if(livePatient == null) {
                    missingField(txtPatientId);
                    return;
                }
                newPatient.setPatientId(Integer.parseInt(patientId));
                newPatient.setNurseId(nurseId);

                if(firstName.length() == 0) {
                    missingField(txtFirstName);
                    return;
                }
                newPatient.setFirstName(firstName);

                if(lastName.length() == 0) {
                    missingField(txtLastName);
                    return;
                }
                newPatient.setLastName(lastName);

                if(department.length() == 0) {
                    missingField(txtDepartment);
                    return;
                }
                newPatient.setDepartment(department);

                if(roomNumber.length() == 0) {
                    missingField(txtRoomNumber);
                    return;
                }
                newPatient.setRoomNumber(Integer.parseInt(roomNumber));


                patientViewModel.update(newPatient);
            } catch (Exception e) {
                Toast.makeText(UpdateActivity.this, "Invalid form values", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        //observers
        patientViewModel.getPatient().observe(this, patient -> {
            livePatient = patient;
            if(livePatient != null) {
                txtFirstName.setText(livePatient.getFirstName());
                txtLastName.setText(livePatient.getLastName());
                txtDepartment.setText(livePatient.getDepartment());
                txtRoomNumber.setText("" + livePatient.getRoomNumber());
            }
            else {
                txtFirstName.setText("");
                txtLastName.setText("");
                txtDepartment.setText("");
                txtRoomNumber.setText("");
            }
        });

        patientViewModel.getIntResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer result) {
                switch(result) {
                    // Update Success
                    case 2:
                        Toast.makeText(UpdateActivity.this, "Patient successfully updated", Toast.LENGTH_SHORT).show();
                        break;
                    // Update Failed
                    case -1:
                        Toast.makeText(UpdateActivity.this, "Error updating newPatient", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void missingField(EditText txtField) {
        txtField.requestFocus();
        txtField.setError("Required Field");
    }
}
