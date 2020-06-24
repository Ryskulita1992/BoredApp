package kg.geektech.todo;
import android.app.Application;
import kg.geektech.todo.data.database.AppPreferences;


public class App extends Application {

    private static AppPreferences appPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        appPreferences= new AppPreferences(this);
    }

}
