package com.example.floraad.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.floraad.R;

import java.util.List;

public class ListarFloraFragment extends Fragment {

    Button btflora, btimagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listar_flora, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //NavHostFragment.findNavController(ListarFloraFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
        init();
    }

    private void init() {
        btflora = getView().findViewById(R.id.btAddFlora);
        btimagen = getView().findViewById(R.id.btListarImagenes);

        btimagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ListarFloraFragment.this).navigate(R.id.action_ListarFloraFragment_to_ListarImagenFragment);
            }
        });

        btflora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ListarFloraFragment.this).navigate(R.id.action_ListarFloraFragment_to_addFloraFragment);
            }
        });


        private void cargaRecycler() {
            viewModel.getAllMoviles();

            RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewFlora);
            AdapterRecyclerMovil adapter = new AdapterRecyclerMovil(moviles,getActivity(),getView());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            viewModel.getListMutableLiveDataMoviles().observe(getActivity(), new Observer<List<Movil>>() {
                @Override
                public void onChanged(List<Movil> m) {
                    moviles.clear();
                    moviles.addAll(m);
                    adapter.notifyDataSetChanged();
                }
            });
        }

    }

}