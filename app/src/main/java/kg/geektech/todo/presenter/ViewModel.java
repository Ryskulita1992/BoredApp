package kg.geektech.todo.presenter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import kg.Repository;
import kg.geektech.todo.model.BoredAction;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<BoredAction>> liveDataAllActions;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        liveDataAllActions=repository.getAllList();
    }
    public void insert (BoredAction boredAction){
        repository.insert(boredAction);

    }
    public void delete (BoredAction boredAction){
        repository.delete(boredAction);

    }
    public void deleteAll (BoredAction boredAction){
        repository.deleteAll(boredAction);

    }
    public void update (BoredAction boredAction){
        repository.update(boredAction);

    }
    public LiveData<List<BoredAction>> getLiveDataAllActions(){
        return liveDataAllActions;

    }





}
