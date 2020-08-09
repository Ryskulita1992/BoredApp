package kg.geektech.todo.presenter.favorites;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import kg.Repository;
import kg.geektech.todo.model.BoredAction;

public class FavoritesViewModel extends ViewModel {
    private Repository repository;
    private LiveData<List<BoredAction>> liveDataAllActions;

    public FavoritesViewModel(@NonNull Application application) {
        super();
        Executor executor= Executors.newSingleThreadExecutor();//creates the new thread and manage its lifecycle
        Executor executor1=Executors.newFixedThreadPool(3);
        executor1.execute(new Runnable() {
            @Override
            public void run() {
                Log.e("yoyo", "Thread" +Thread.currentThread().getName());


            }
        });
        Log.e("yoyo", "Thread" +Thread.currentThread().getName());
        repository=new Repository(application);
        liveDataAllActions=repository.getAllList();
    }







}
