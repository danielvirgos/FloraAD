package com.example.floraad.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.floraad.R;
import com.example.floraad.databinding.FragmentListarFloraBinding;
import com.example.floraad.view.adapters.AdapterRecyclerFlora;
import com.example.floraad.viewmodel.ViewModel;

import java.util.List;

public class ListarFloraFragment extends Fragment {

    Button btflora;
    ViewModel viewModel;
    private FragmentListarFloraBinding binding;

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

        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        btflora = getView().findViewById(R.id.btAddFlora);
        btflora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ListarFloraFragment.this).navigate(R.id.action_ListarFloraFragment_to_addFloraFragment);
            }
        });

        AdapterRecyclerFlora floraAdapter = new AdapterRecyclerFlora(getContext(), getActivity(), getView());
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewFlora);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(floraAdapter);

        viewModel.getFloraLiveData().observe(this, floraPlural -> {
            Log.v("xyzyx", floraPlural.toString());
            floraAdapter.setFloraList(floraPlural);

        });
        viewModel.getFlora();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}