package com.example.floraad.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.floraad.R;
import com.example.floraad.databinding.FragmentAddFloraBinding;
import com.example.floraad.databinding.FragmentAddImagenBinding;
import com.example.floraad.model.entity.Imagen;
import com.example.floraad.viewmodel.ViewModel;


public class AddImagenFragment extends Fragment {

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private Intent resultadoImagen = null;
    FragmentAddImagenBinding binding;
    private ViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
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
        Long idflora = Long.parseLong(binding.etAddIdFlora.getText().toString());
        if (!(nombre.trim().isEmpty() || descripcion.trim().isEmpty() || resultadoImagen == null)) {
            imagen.idflora = idflora;
            imagen.nombre = nombre;
            imagen.descripcion = descripcion;
            viewModel.saveImagen(resultadoImagen, imagen);
            NavHostFragment.findNavController(AddImagenFragment.this)
                    .navigate(R.id.action_addImagenFragment_to_ListarFloraFragment);
        }
    }
}