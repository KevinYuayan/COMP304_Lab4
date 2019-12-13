package com.example.johnyuayan_comp304lab4;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class OptionsMenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options_menu, container, false);
    }

    // Options Menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    // Event Handlers
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        switch(item.getItemId()) {
            case R.id.login:
                intent  = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                return true;
            case R.id.patient:
                intent  = new Intent(getActivity(), PatientActivity.class);
                getActivity().startActivity(intent);
                return true;
            case R.id.test:
                intent  = new Intent(getActivity(), TestActivity.class);
                getActivity().startActivity(intent);
                return true;
            case R.id.update:
                intent  = new Intent(getActivity(), UpdateActivity.class);
                getActivity().startActivity(intent);
                return true;
            case R.id.viewTest:
                intent  = new Intent(getActivity(), ViewTestActivity.class);
                getActivity().startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
