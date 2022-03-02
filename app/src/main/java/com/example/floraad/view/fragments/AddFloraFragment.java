package com.example.floraad.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.floraad.R;


public class AddFloraFragment extends Fragment {

    Button btContinuar, btCancelar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_flora, container, false);
    }

    private void init() {

        btContinuar = getView().findViewById(R.id.btAvanzaAdd);
        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(AddFloraFragment.this).navigate(R.id.action_addFloraFragment_to_addImagenFragment);
            }
        });

        btCancelar = getView().findViewById(R.id.btRetrocedeInicioAdd);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(AddFloraFragment.this).navigate(R.id.action_addFloraFragment_to_ListarFloraFragment);
            }
        });


    }

}