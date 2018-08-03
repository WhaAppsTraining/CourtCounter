package whaapps.courtcounter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import whaapps.courtcounter.data.Game;
import whaapps.courtcounter.data.GameRepository;

/**
 * @author hendrawd on 28/07/18
 */
public class GameViewModel extends AndroidViewModel {
    private GameRepository mRepository;
    private LiveData<Game> gameLiveData;

    public GameViewModel(Application application) {
        super(application);
        mRepository = new GameRepository(application);
        gameLiveData = mRepository.loadGame();

        if (gameLiveData.getValue() == null) {
            mRepository.insert(createNewGame());
        }
    }

    private void setGame(Game game) {
        mRepository.insert(game);
    }

    private Game getGame() {
        if (gameLiveData.getValue() != null) {
            return gameLiveData.getValue();
        }
        return createNewGame();
    }

    private Game createNewGame() {
        return new Game(
                GameRepository.GAME_TITLE,
                GameRepository.TEAM_A_NAME,
                GameRepository.TEAM_B_NAME,
                0,
                0
        );
    }

    public LiveData<Game> getGameLiveData() {
        return gameLiveData;
    }

    public void addToTeamA(int amount) {
        Game game = getGame();
        int newScore = game.getScoreTeamA() + amount;
        game.setScoreTeamA(newScore);
        mRepository.insert(game);
    }

    public void addToTeamB(int amount) {
        Game game = getGame();
        int newScore = game.getScoreTeamB() + amount;
        game.setScoreTeamB(newScore);
        mRepository.insert(game);
    }

    public void setTeamAName(final String name) {
        Game game = getGame();
        game.setTeamAName(name);
        mRepository.insert(game);
    }

    public void setTeamBName(final String name) {
        Game game = getGame();
        game.setTeamBName(name);
        mRepository.insert(game);
    }

    public void setGameTitle(final String title) {
        Game game = getGame();
        game.setTitle(title);
        mRepository.insert(game);
    }

    public void resetScores() {
        Game game = getGame();
        game.setScoreTeamA(0);
        game.setScoreTeamB(0);
        mRepository.insert(game);
    }
}
