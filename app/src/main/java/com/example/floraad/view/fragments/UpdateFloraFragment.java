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
import android.widget.Toast;

import com.example.floraad.R;
import com.example.floraad.databinding.FragmentUpdateFloraBinding;
import com.example.floraad.model.entity.Flora;
import com.example.floraad.viewmodel.ViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateFloraFragment extends Fragment {

    private FragmentUpdateFloraBinding binding;
    private Flora flora = new Flora();
    private ViewModel viewModel;
    private Bundle bundle = new Bundle();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        bundle = getArguments();
        flora = bundle.getParcelable("flora");

        rellenoCampos();

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getEditFloraLiveData().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if(aLong >= 0) {

                }
            }
        });

        /*viewModel.getDeleteFloraLiveData().observe(this, new Observer<Long>() {
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
        });*/

        binding.btAvanzaEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateFlora();
            }
        });

        binding.btRetrocedeInicioEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(UpdateFloraFragment.this).navigate(R.id.action_updateFloraFragment_to_ListarFloraFragment);
            }
        });

    }

    private void rellenoCampos() {
        binding.etEditNombre.setText(flora.getNombre());
        binding.etEditFamilia.setText(flora.getFamilia());
        binding.etEditIdentificacion.setText(flora.getIdentificacion());
        binding.etAEditAltitud.setText(flora.getAltitud());
        binding.etEditHabitat.setText(flora.getHabitat());
        binding.etEditFitosociologia.setText(flora.getFitosociologia());
        binding.etEditBiotipo.setText(flora.getBiotipo());
        binding.etEditBiologiaReproductiva.setText(flora.getBiologia_reproductiva());
        binding.etEditFloracion.setText(flora.getFloracion());
        binding.etEditFructificacion.setText(flora.getFructificacion());
        binding.etEditExpresionSexual.setText(flora.getExpresion_sexual());
        binding.etEditPolinizacion.setText(flora.getPolinizacion());
        binding.etEditDispersion.setText(flora.getDispersion());
        binding.etEditNumeroCrosomatico.setText(flora.getNumero_cromosomatico());
        binding.etEditReproduccionAsexual.setText(flora.getReproduccion_asexual());
        binding.etEditDistribucion.setText(flora.getDistribucion());
        binding.etEditBiologia.setText(flora.getBiologia());
        binding.etEditDemografia.setText(flora.getDemografia());
        binding.etEditMedidas.setText(flora.getMedidas_propuestas());
        binding.etEditAmenazas.setText(flora.getAmenazas());
    }

    private void updateFlora() {
        Log.v("zzzzz", "Recopilos los datos de los campos");
        viewModel.editFlora(flora.getId(),recopilaDatos());
        Log.v("zzzzz", "Flora Editada");
        AddFloraFragment.mode=2;
        NavHostFragment.findNavController(UpdateFloraFragment.this).navigate(R.id.action_updateFloraFragment_to_ListarFloraFragment);

        Toast.makeText(getContext(), "Asegurese de rellenar todos los campos", Toast.LENGTH_SHORT);

    }

    private Flora recopilaDatos() {
        Flora flora = new Flora();
        flora.setAltitud(binding.etAEditAltitud.getText().toString());
        flora.setAmenazas(binding.etEditAmenazas.getText().toString());
        flora.setBiologia(binding.etEditBiologia.getText().toString());
        flora.setNumero_cromosomatico(binding.etEditNumeroCrosomatico.getText().toString());
        flora.setIdentificacion(binding.etEditIdentificacion.getText().toString());
        flora.setNombre(binding.etEditNombre.getText().toString());
        flora.setHabitat(binding.etEditHabitat.getText().toString());
        flora.setFloracion(binding.etEditFloracion.getText().toString());
        flora.setFitosociologia(binding.etEditFitosociologia.getText().toString());
        flora.setFamilia(binding.etEditFamilia.getText().toString());
        flora.setExpresion_sexual(binding.etEditExpresionSexual.getText().toString());
        flora.setDistribucion(binding.etEditDistribucion.getText().toString());
        flora.setDispersion(binding.etEditDispersion.getText().toString());
        flora.setDemografia(binding.etEditDemografia.getText().toString());
        flora.setBiologia_reproductiva(binding.etEditBiologiaReproductiva.getText().toString());
        flora.setBiotipo(binding.etEditBiotipo.getText().toString());
        flora.setFructificacion(binding.etEditFructificacion.getText().toString());
        flora.setMedidas_propuestas(binding.etEditMedidas.getText().toString());
        flora.setPolinizacion(binding.etEditPolinizacion.getText().toString());
        flora.setReproduccion_asexual(binding.etEditReproduccionAsexual.getText().toString());
        return flora;
    }

    private boolean comprobarCamposFlora() {
        Log.v("zzzzz", "Compruebo que todos los campos estan rellenos");
        if(validoCampoRelleno(binding.etAEditAltitud)
                || validoCampoRelleno(binding.etEditAmenazas)
                || validoCampoRelleno(binding.etEditBiologia)
                || validoCampoRelleno(binding.etEditNumeroCrosomatico)
                || validoCampoRelleno(binding.etEditIdentificacion)
                || validoCampoRelleno(binding.etEditNombre)
                || validoCampoRelleno(binding.etEditHabitat)
                || validoCampoRelleno(binding.etEditFloracion)
                || validoCampoRelleno(binding.etEditFitosociologia)
                || validoCampoRelleno(binding.etEditFamilia)
                || validoCampoRelleno(binding.etEditExpresionSexual)
                || validoCampoRelleno(binding.etEditDispersion)
                || validoCampoRelleno(binding.etEditDistribucion)
                || validoCampoRelleno(binding.etEditDemografia)
                || validoCampoRelleno(binding.etEditBiologiaReproductiva)
                || validoCampoRelleno(binding.etEditBiotipo)
                || validoCampoRelleno(binding.etEditFructificacion)
                || validoCampoRelleno(binding.etEditMedidas)
                || validoCampoRelleno(binding.etEditReproduccionAsexual)
                || validoCampoRelleno(binding.etEditPolinizacion)
        ) {
            Log.v("zzzz", "No estan rellenos todos los campos");
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