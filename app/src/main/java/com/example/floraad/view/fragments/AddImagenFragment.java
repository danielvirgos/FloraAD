package com.example.floraad.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.floraad.R;
import com.example.floraad.databinding.FragmentAddFloraBinding;
import com.example.floraad.databinding.FragmentAddImagenBinding;
import com.example.floraad.model.entity.Flora;
import com.example.floraad.model.entity.Imagen;
import com.example.floraad.viewmodel.ViewModel;

import java.util.ArrayList;


public class AddImagenFragment extends Fragment {

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private Intent resultadoImagen = null;
    FragmentAddImagenBinding binding;
    private ViewModel viewModel;
    Bundle bundle = new Bundle();
    Long id;
    public Flora flora;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddImagenBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    private void init() {

        bundle = getArguments();
        activityResultLauncher = getLauncher();
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        MutableLiveData<ArrayList<Flora>> floraList = viewModel.getFloraLiveData();
        viewModel.getFlora();
        Log.v("zzzz", ""+ AddFloraFragment.mode);
        floraList.observe(this,  floras -> {
            Log.v("zzzz", "Activo el observe");
            if(AddFloraFragment.mode == 1) {
                id =  floras.get(viewModel.getFloraLiveData().getValue().size() - 1).getId();
                binding.etAddIdFlora.setText(floras.get(viewModel.getFloraLiveData().getValue().size() - 1).getNombre() + "");
                Log.v("zzzz", " " + id);
            }else if(AddFloraFragment.mode == 2){
                flora = bundle.getParcelable("flora");
                id = flora.getId();
                binding.etAddIdFlora.setText(String.valueOf(flora.getNombre()));
                Log.v("zzzz", " " + id);
            }
            AddFloraFragment.mode=0;
        });

        binding.btRetrocedeAddFlora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(AddImagenFragment.this).navigate(R.id.action_addImagenFragment_to_addFloraFragment);
            }
        });

        binding.btFinalizarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirImagen();
            }
        });

        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionaImagen();
            }
        });
    }

    ActivityResultLauncher<Intent> getLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //respuesta al resultado de haber seleccionado una imagen
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        resultadoImagen = result.getData();
                        binding.imgAdd.setImageURI(resultadoImagen.getData());
                    }
                }
        );
    }
    Intent getContentIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

    private void seleccionaImagen() {
        Intent intent = getContentIntent();
        activityResultLauncher.launch(intent);
    }

    private void subirImagen() {
        Imagen imagen = new Imagen();
        String nombre = binding.etAddNombreImagen.getText().toString();
        String descripcion = binding.etAddDescripcionImg.getText().toString();

        if (!(nombre.trim().isEmpty() || descripcion.trim().isEmpty() || resultadoImagen == null)) {
            imagen.idflora = id;
            imagen.nombre = nombre;
            imagen.descripcion = descripcion;
            viewModel.saveImagen(resultadoImagen, imagen);
            NavHostFragment.findNavController(AddImagenFragment.this)
                    .navigate(R.id.action_addImagenFragment_to_ListarFloraFragment);
        }
    }
}