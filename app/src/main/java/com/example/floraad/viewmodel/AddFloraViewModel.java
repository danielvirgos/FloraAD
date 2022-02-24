package com.example.floraad.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.floraad.model.Repository;
import com.example.floraad.model.entity.Flora;

public class AddFloraViewModel extends AndroidViewModel {

    private Repository repository;

    public AddFloraViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Long> getAddFloraLiveData() {

        return repository.getAddFloraLiveData();
    }

    public void createFlora(Flora flora) {
        repository.createFlora(flora);
    }
}