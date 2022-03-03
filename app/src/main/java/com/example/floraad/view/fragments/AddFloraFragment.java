package com.example.floraad.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.floraad.R;
import com.example.floraad.databinding.FragmentAddFloraBinding;
import com.example.floraad.model.entity.Flora;
import com.example.floraad.viewmodel.ViewModel;
import com.google.android.material.textfield.TextInputEditText;


public class AddFloraFragment extends Fragment {

    Button btContinuar, btCancelar;
    ViewModel viewModel;
    FragmentAddFloraBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddFloraBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        btContinuar = getView().findViewById(R.id.btAvanzaAdd);
        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearFlora();
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

    private void crearFlora() {
        if(comprobarCamposFlora()) {
            viewModel.createFlora(recopilaDatos());
            NavHostFragment.findNavController(AddFloraFragment.this).navigate(R.id.action_addFloraFragment_to_addImagenFragment);
        } else {
            Toast.makeText(getContext(), "Asegurese de rellenar todos los campos", Toast.LENGTH_SHORT);
        }
    }

    private Flora recopilaDatos() {
        Flora flora = new Flora();
        flora.setAltitud(binding.etAddAltitud.getText().toString());
        flora.setAmenazas(binding.etAddAmenazas.getText().toString());
        flora.setBiologia(binding.etAddBiologia.getText().toString());
        flora.setNumero_cromosomatico(binding.etAddNumeroCrosomatico.getText().toString());
        flora.setIdentificacion(binding.etAddIdentificacion.getText().toString());
        flora.setNombre(binding.etAddNombre.getText().toString());
        flora.setHabitat(binding.etAddHabitat.getText().toString());
        flora.setFloracion(binding.etAddFloracion.getText().toString());
        flora.setFitosociologia(binding.etAddFitosociologia.getText().toString());
        flora.setFamilia(binding.etAddFamilia.getText().toString());
        flora.setExpresion_sexual(binding.etAddExpresionSexual.getText().toString());
        flora.setDistribucion(binding.etAddDistribucion.getText().toString());
        flora.setDispersion(binding.etAddDispersion.getText().toString());
        flora.setDemografia(binding.etAddDemografia.getText().toString());
        flora.setBiologia_reproductiva(binding.etAddBiologiaReproductiva.getText().toString());
        flora.setBiotipo(binding.etAddBiotipo.getText().toString());
        flora.setFructificacion(binding.etAddFructificacion.getText().toString());
        flora.setMedidas_propuestas(binding.etAddMedidas.getText().toString());
        flora.setId(Long.parseLong(binding.etAddId.getText().toString()));
        return flora;
    }

    private boolean comprobarCamposFlora() {
        if(validoCampoRelleno(binding.etAddAltitud)
                || validoCampoRelleno(binding.etAddAmenazas)
                || validoCampoRelleno(binding.etAddBiologia)
                || validoCampoRelleno(binding.etAddNumeroCrosomatico)
                || validoCampoRelleno(binding.etAddIdentificacion)
                || validoCampoRelleno(binding.etAddNombre)
                || validoCampoRelleno(binding.etAddHabitat)
                || validoCampoRelleno(binding.etAddFloracion)
                || validoCampoRelleno(binding.etAddFitosociologia)
                || validoCampoRelleno(binding.etAddFamilia)
                || validoCampoRelleno(binding.etAddExpresionSexual)
                || validoCampoRelleno(binding.etAddDistribucion)
                || validoCampoRelleno(binding.etAddDispersion)
                || validoCampoRelleno(binding.etAddDemografia)
                || validoCampoRelleno(binding.etAddBiologiaReproductiva)
                || validoCampoRelleno(binding.etAddBiotipo)
                || validoCampoRelleno(binding.etAddFructificacion)
                || validoCampoRelleno(binding.etAddMedidas)
                || validoCampoRelleno(binding.etAddId)
        ) {
            return false;
        } else {
            return true;
        }
    }

    public  static   boolean validoCampoRelleno (TextInputEditText editalo){
        if(editalo.getText().toString().isEmpty() || editalo.getText().toString() == null){
            editalo.setError("Error debes rellenar el campo de texto");
            return false;
        }
        else{
            return true;
        }
    }

}