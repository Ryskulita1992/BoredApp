package kg;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
import kg.geektech.todo.data.data.database.BoredDatabase;
import kg.geektech.todo.data.data.local.BoredDao;
import kg.geektech.todo.model.BoredAction;

public class Repository {
    private BoredDao boredDao;
    private LiveData<List<BoredAction>> allList;

    public  Repository (Application application){
        BoredDatabase boredDatabase=BoredDatabase.getInstance(application);
        boredDao=boredDatabase.boredDao();
        allList=boredDao.getLiveData();

    }
    public void insert(BoredAction boredAction){
        new InsertBoredActionAsyncList(boredDao).execute(boredAction);

    }
    public void update(BoredAction boredAction){
        new UpdateBoredActionAsyncList(boredDao).execute(boredAction);


    }
    public void delete(BoredAction boredAction){
        new DeleteBoredActionAsyncList(boredDao).execute(boredAction);


    }
    public void deleteAll(BoredAction boredAction){
        new DeleteAllBoredActionAsyncList(boredDao).execute();


    }
    public  LiveData<List<BoredAction>>getAllList(){
        return allList;

    }
    private static class InsertBoredActionAsyncList extends AsyncTask <BoredAction, Void, Void>{
        private BoredDao boredDao;
        private InsertBoredActionAsyncList(BoredDao boredDao){
            this.boredDao=boredDao;
        }
        @Override
        protected Void doInBackground(BoredAction... boredActions) {
           boredDao.insert(boredActions[0]);
            return null;
        }
    }
    private static class UpdateBoredActionAsyncList extends AsyncTask <BoredAction, Void, Void>{
        private BoredDao boredDao;
        private UpdateBoredActionAsyncList(BoredDao boredDao){
            this.boredDao=boredDao;
        }
        @Override
        protected Void doInBackground(BoredAction... boredActions) {
            boredDao.update(boredActions[0]);
            return null;
        }
    }
    private static class DeleteBoredActionAsyncList extends AsyncTask <BoredAction, Void, Void>{
        private BoredDao boredDao;
        private DeleteBoredActionAsyncList(BoredDao boredDao){
            this.boredDao=boredDao;
        }
        @Override
        protected Void doInBackground(BoredAction... boredActions) {
            boredDao.delete(boredActions[0]);
            return null;
        }
    }
    private static class DeleteAllBoredActionAsyncList extends AsyncTask <Void, Void, Void>{
        private BoredDao boredDao;
        private DeleteAllBoredActionAsyncList(BoredDao boredDao){
            this.boredDao=boredDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            boredDao.deleteAllFavoritesList();
            return null;
        }
    }



}
