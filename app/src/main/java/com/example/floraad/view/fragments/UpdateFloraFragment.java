package com.example.floraad.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.floraad.R;
import com.example.floraad.databinding.FragmentListarFloraBinding;
import com.example.floraad.databinding.FragmentUpdateFloraBinding;
import com.example.floraad.model.entity.Flora;
import com.example.floraad.viewmodel.ViewModel;

public class UpdateFloraFragment extends Fragment {

    private FragmentUpdateFloraBinding binding;
    private Flora flora = new Flora();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateFloraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    private void init() {
        Bundle bundle = new Bundle();
        bundle = getArguments();
        flora = bundle.getParcelable("flora");

        seteandFloras();

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getEditFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong >= 0) {

                }
            }
        });

        viewModel.getDeleteFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong >=0) {

                }
            }
        });

        viewModel.getDeleteImagenLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong >=0) {

                }
            }
        });

        binding.btAvanzaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertEdit(viewModel);
            }
        });

        binding.btRetrocedeInicioEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(UpdateFloraFragment.this).navigate(R.id.action_updateFloraFragment_to_ListarFloraFragment);
            }
        });

    }

}