package whaapps.courtcounter.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

public class GameRepository {

    public static final String GAME_TITLE = "August 18 Playoff";
    public static final String TEAM_A_NAME = "Cupcake";
    public static final String TEAM_B_NAME = "Donut";
    private GameDao mGameDao;
    private LiveData<Game> mGameLiveData;

    public GameRepository(Application application) {
        GameRoomDatabase db = GameRoomDatabase.getDatabase(application);
        mGameDao = db.gameDao();
        mGameLiveData = mGameDao.loadGame(GAME_TITLE);
    }

    public LiveData<Game> loadGame() {
        return mGameLiveData;
    }

    public void insert(Game game) {
        new insertAsyncTask(mGameDao).execute(game);
    }

    private static class insertAsyncTask extends AsyncTask<Game, Void, Void> {

        private GameDao mAsyncTaskDao;

        insertAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}