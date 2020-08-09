package kg.geektech.todo.data.data.local;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import kg.geektech.todo.model.BoredAction;

@Dao
public interface BoredDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BoredAction boredAction);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(BoredAction boredAction);

    @Query("SELECT *FROM bored_action WHERE unique_key IS :key")
    BoredAction get(String key);



    @Query("SELECT *FROM bored_action")
    List <BoredAction> getAll();

    @Delete
    void delete(BoredAction boredAction);


    @Query("SELECT * FROM bored_action")
    LiveData<List<BoredAction>> getLiveData();


    @Query("DELETE FROM bored_action")//will delete all added items to the list
    void deleteAllFavoritesList();


}
