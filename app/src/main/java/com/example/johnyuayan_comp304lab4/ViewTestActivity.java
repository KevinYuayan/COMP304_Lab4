package com.example.johnyuayan_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.johnyuayan_comp304lab4.Test.Test;
import com.example.johnyuayan_comp304lab4.Test.TestViewModel;


public class ViewTestActivity extends AppCompatActivity {
    EditText txtPatientId;
    ListView lstTestDisplay;

    private SharedPreferences myPreference;
    private SharedPreferences.Editor prefEditor;

    private TestViewModel testViewModel;
    private int nurseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        // Create the SharedPreference
        myPreference = getSharedPreferences("Login", 0);
        //prepare it for edit by creating and Edit object
        prefEditor = myPreference.edit();

        nurseId = myPreference.getInt("id", -1);

        if(nurseId == -1) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        lstTestDisplay = findViewById(R.id.lstTest);
        txtPatientId = findViewById(R.id.searchPatientIDText);

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
                    testViewModel.setPatientID(Integer.parseInt(editable.toString()));
                }
            }
        });

        //observers

        testViewModel.getPatientTests().observe(this, patientTests ->{
            ArrayAdapter<Test> testsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patientTests);
            lstTestDisplay.setAdapter(testsAdapter);
        });
    }
}
