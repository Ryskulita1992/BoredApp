package kg.geektech.todo;
import android.app.Application;
import kg.geektech.todo.data.database.AppPreferences;
import kg.geektech.todo.model.BoredApiClient;


public class App extends Application {

    private static AppPreferences appPreferences;
    public static BoredApiClient boredApiClient;




    @Override
    public void onCreate() {
        super.onCreate();
        appPreferences= new AppPreferences(this);
        boredApiClient=new BoredApiClient();
    }

}
