package kg.geektech.todo.data.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private SharedPreferences preferences;
    private final static String PREF_IS_FIRST_LAUNCH= "is_first_launch";
    private final static String PREF_NAME= "bored_app_pref";
    public final static String KEY_NIGHT_MODE= "isNightMode";


    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

    }
    public boolean isFirstLaunched(){
     return preferences.getBoolean( PREF_IS_FIRST_LAUNCH , true);
    }

    public void setLaunched() {
        preferences.edit().putBoolean( PREF_IS_FIRST_LAUNCH, false).apply();

    }




}


