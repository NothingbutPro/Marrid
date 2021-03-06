package com.ics.meem.ui.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ics.meem.R;

public class contactusFragment extends Fragment {

    private ContactusViewModel contactusViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactusViewModel =
                ViewModelProviders.of(this).get(ContactusViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);
        final TextView textView = root.findViewById(R.id.text_contacts);
        contactusViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}