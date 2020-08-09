package kg.geektech.todo;
import android.app.Application;

import androidx.room.Room;

import kg.Repository;
import kg.geektech.todo.data.data.AppPreferences;
import kg.geektech.todo.data.data.database.BoredDatabase;
import kg.geektech.todo.data.data.local.BoredStorage;
import kg.geektech.todo.model.BoredApiClient;


public class App extends Application {
    private static BoredDatabase boredDatabase;
    public static AppPreferences appPreferences;
    public static BoredApiClient boredApiClient;
    public static BoredStorage boredStorage;
    public static Repository repository;
    //public static ViewModel viewModel;


    @Override
    public void onCreate() {
        super.onCreate();
        appPreferences= new AppPreferences(this);
        boredApiClient=new BoredApiClient();
        requestDatabaseBuilder();
        boredStorage= new BoredStorage(boredDatabase.boredDao());
                //(boredDatabase.boredDao());

        //viewModel= ViewModelProvider.of
    }

    public void requestDatabaseBuilder(){
        boredDatabase= Room.databaseBuilder(this,
                BoredDatabase.class,
                "bored.database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

    }

}
