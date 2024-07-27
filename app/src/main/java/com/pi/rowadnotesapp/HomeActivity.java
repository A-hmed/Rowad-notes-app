package com.pi.rowadnotesapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pi.rowadnotesapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.main);
        binding.addBtn.setOnClickListener(view -> {
            AddNoteFragment fragment = new AddNoteFragment();
            fragment.show(getSupportFragmentManager(), null);
        });
    }
}