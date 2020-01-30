package com.ics.meem.ui.registration;

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

public class RegistrationFragment extends Fragment {

    private RegidtrationViewModel regidtrationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        regidtrationViewModel =
                ViewModelProviders.of(this).get(RegidtrationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_resistration, container, false);
        final TextView textView = root.findViewById(R.id.text_registation);
        regidtrationViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}