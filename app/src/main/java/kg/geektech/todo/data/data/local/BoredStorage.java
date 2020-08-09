package kg.geektech.todo.data.data.local;
import java.util.List;
import kg.geektech.todo.model.BoredAction;



public class BoredStorage {

    private BoredDao dao;

    public BoredStorage (BoredDao dao){
        this.dao=dao;
    }
    public void saveBoredAction(BoredAction boredAction){
        dao.insert(boredAction);
    }
    public BoredAction getBoredAction(String key){
        return dao.get(key);
    }

    public List<BoredAction> getAllActions(){
        return dao.getAll();
    }

    public void deleteBoredAction (BoredAction boredAction){
        dao.delete(boredAction);
    }


    public void getAllAlive() {
        dao.getLiveData();
    }
}
