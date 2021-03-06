package com.example.floraad.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.floraad.model.Repository;
import com.example.floraad.model.entity.Flora;
import com.example.floraad.model.entity.Imagen;

import java.util.ArrayList;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<String> mText;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        mText = new MutableLiveData<>();
    }

    //--------------------Methods AddFlora-------------------------------

        public MutableLiveData<Long> getAddFloraLiveData() {

        return repository.getAddFloraLiveData();
    }

        public void createFlora(Flora flora) {
        repository.createFlora(flora);
    }

    //--------------------Methods EditFlora-------------------------------

    public MutableLiveData<Long> getEditFloraLiveData() {

        return repository.getEditFloraLiveData();
    }

    public void editFlora(long id, Flora flora) {
        repository.editFlora(id, flora);
    }


    //--------------------Methods AddImagen-------------------------------

    public MutableLiveData<Long> getAddImagenLiveData() {
        return repository.getAddImagenLiveData();
    }

    public void saveImagen(Intent intent, Imagen imagen) {
        repository.saveImagen(intent, imagen);
    }

    //--------------------Methods DeleteFlora-------------------------------

    public MutableLiveData<Long> getDeleteFloraLiveData() {
        return repository.getDeleteFloraLiveData();
    }

    public void deleteFlora(long id) {
        repository.deleteFlora(id);
    }

    //--------------------Methods DeleteImagen-------------------------------

    public MutableLiveData<Long> getDeleteImagenLiveData() {
        return repository.getDeleteImagenLiveData();
    }

    public void deleteImagen(long id) {
        repository.deleteImagen(id);
    }

    //--------------------Methods ListFlora----------------------------------

    public MutableLiveData<ArrayList<Flora>> getFloraLiveData() {
        return repository.getFloraLiveData();
    }

    public void getFlora() {
        repository.getFlora();
    }
    public LiveData<String> getText() {
        return mText;
    }
}
