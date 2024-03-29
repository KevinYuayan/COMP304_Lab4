package com.example.johnyuayan_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    private final AppCompatActivity activity = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.PatientButton:
                intent = new Intent(activity, PatientActivity.class);
                startActivity(intent);
                break;
            case R.id.UpdatePatientButton:
                intent = new Intent(activity, UpdateActivity.class);
                startActivity(intent);
                break;
            case R.id.TestButton:
                intent = new Intent(activity, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.ViewTestButton:
                intent = new Intent(activity, ViewTestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
