package com.example.floraad.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
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

    public static int mode;
    Button btContinuar, btCancelar;
    ViewModel viewModel;
    FragmentAddFloraBinding binding;
    Bundle bundle = new Bundle();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        mode = 0;
        viewModel.getAddFloraLiveData().observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong > 0) {

                    NavHostFragment.findNavController(AddFloraFragment.this)
                            .navigate(R.id.action_addFloraFragment_to_addImagenFragment);
                    mode=1;
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

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
            bundle.putParcelable("flora", recopilaDatos());
            Log.v("zzzz", recopilaDatos().toString());
            NavHostFragment.findNavController(AddFloraFragment.this).navigate(R.id.action_addFloraFragment_to_addImagenFragment, bundle);
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
        flora.setPolinizacion(binding.etAddPolinizacion.getText().toString());
        flora.setReproduccion_asexual(binding.etAddReproduccionAsexual.getText().toString());

        return flora;
    }

    private boolean comprobarCamposFlora() {
        if(validoCampoRellenoAdd(binding.etAddAltitud)
                || validoCampoRellenoAdd(binding.etAddAmenazas)
                || validoCampoRellenoAdd(binding.etAddBiologia)
                || validoCampoRellenoAdd(binding.etAddNumeroCrosomatico)
                || validoCampoRellenoAdd(binding.etAddIdentificacion)
                || validoCampoRellenoAdd(binding.etAddNombre)
                || validoCampoRellenoAdd(binding.etAddHabitat)
                || validoCampoRellenoAdd(binding.etAddFloracion)
                || validoCampoRellenoAdd(binding.etAddFitosociologia)
                || validoCampoRellenoAdd(binding.etAddFamilia)
                || validoCampoRellenoAdd(binding.etAddExpresionSexual)
                || validoCampoRellenoAdd(binding.etAddDistribucion)
                || validoCampoRellenoAdd(binding.etAddDispersion)
                || validoCampoRellenoAdd(binding.etAddDemografia)
                || validoCampoRellenoAdd(binding.etAddBiologiaReproductiva)
                || validoCampoRellenoAdd(binding.etAddBiotipo)
                || validoCampoRellenoAdd(binding.etAddFructificacion)
                || validoCampoRellenoAdd(binding.etAddMedidas)
                || validoCampoRellenoAdd(binding.etAddReproduccionAsexual)
                || validoCampoRellenoAdd(binding.etAddPolinizacion)
        ) {
            return true;
        } else {
            return false;
        }
    }

    public  static   boolean validoCampoRellenoAdd (TextInputEditText editalo){
        if(editalo.getText().toString().isEmpty() || editalo.getText().toString() == null){
            editalo.setError("Error debes rellenar el campo de texto");
            return false;
        }
        else{
            return true;
        }
    }

}