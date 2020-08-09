package kg.geektech.todo.data.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import kg.geektech.todo.data.data.local.BoredDao;
import kg.geektech.todo.model.BoredAction;


@Database(
        entities = {BoredAction.class},
        version = BoredDatabase.VERSION,
        exportSchema = false)

public abstract class BoredDatabase extends RoomDatabase {
    public final static int VERSION = 3;

    public abstract BoredDao boredDao();//

    public static BoredDatabase boredDatabase;//if we want to turn this class to singleTone
    public static BoredDatabase instance;


    //synchronized means only 1 thread at a time can access this method,
    // this way we dom`t create 2 ore more instances of this database
    // when 2 diff threads try to access this method
    public static synchronized BoredDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BoredDatabase.class, "bored_database")
                    .fallbackToDestructiveMigration()//when we increment the version of this database
                    //we have to tell to room how to migrate, if we avoid this the app will crush and will give illegal cast exception
                    //.addCallback(roomCallback)
                    .build();

        }
        return instance;
    }
//    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new PopulateDbAsyncTask(instance).execute();
//        }
//    };
//    private static class PopulateDbAsyncTask extends AsyncTask <Void, Void, Void>{
//        private BoredDao boredDao;
//        private PopulateDbAsyncTask (BoredDatabase db){
//            boredDao=db.boredDao();
//
//        };
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            boredDao.insert(new BoredAction("activity", "type", "oooo", 1, 1.1,0.1);
//
//            return null;
//        }
//    }

    ;


}
