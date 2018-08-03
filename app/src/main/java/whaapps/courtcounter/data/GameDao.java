package whaapps.courtcounter.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * @author hendrawd on 28/07/18
 */
@Dao
public interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Game game);

    @Query("DELETE FROM game_table")
    void deleteAll();

    @Query("SELECT * FROM game_table where title = :title LIMIT 1")
    LiveData<Game> loadGame(String title);

    @Query("SELECT * FROM game_table where title = :title LIMIT 1")
    Game loadGameSync(String title);

    @Query("SELECT * from game_table ORDER BY title ASC")
    LiveData<List<Game>> getAllGames();
}
