package com.pi.rowadnotesapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.pi.rowadnotesapp.database.MyDataBase;
import com.pi.rowadnotesapp.database.enities.Note;
import com.pi.rowadnotesapp.databinding.FragmentAddNoteBinding;

import java.time.LocalDate;

public class AddNoteFragment extends BottomSheetDialogFragment {
    LocalDate selectedDate = LocalDate.now();
    FragmentAddNoteBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateSelectedDateTextView();
        binding.selectedDateTv.setOnClickListener(__ -> {
            DatePickerDialog dialog = new DatePickerDialog(getContext(), (datePicker, year, month, day) -> {
                selectedDate = selectedDate.withYear(year);
                selectedDate = selectedDate.withMonth(month + 1);
                selectedDate = selectedDate.withDayOfMonth(day);
                updateSelectedDateTextView();
            }, selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth());
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog.show();
        });
        binding.addButton.setOnClickListener(__ -> {
            if (isValid()) {
                String title = binding.titleInputLayout.getEditText().getText().toString();
                String description = binding.descriptionInputLayout.getEditText().getText().toString();
                Note newNote = new Note(title, description, selectedDate.toEpochDay());
                MyDataBase.getInstance(getActivity()).getNotesDao().add(newNote);
                dismiss();
            }

        });
    }

    private boolean isValid() {
        String title = binding.titleInputLayout.getEditText().getText().toString();
        String description = binding.descriptionInputLayout.getEditText().getText().toString();
        boolean valid = true;
        if (title.trim().isEmpty()) {
            binding.titleInputLayout.setError("Please enter a valid title");
            valid = false;
        } else {
            binding.titleInputLayout.setError(null);
        }
        if (description.trim().isEmpty()) {
            binding.descriptionInputLayout.setError("Please enter a valid description");
            valid = false;

        } else {
            binding.descriptionInputLayout.setError(null);
        }
        return valid;
    }

    private void updateSelectedDateTextView() {
        binding.selectedDateTv.setText(selectedDate.getDayOfMonth() +
                "/" + selectedDate.getMonthValue() +
                "/" + selectedDate.getYear());
    }
}
