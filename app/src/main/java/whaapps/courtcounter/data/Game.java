package whaapps.courtcounter.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * @author hendrawd on 28/07/18
 */
@Entity(tableName = "game_table")
public class Game {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "team_a_name")
    private String teamAName;
    @ColumnInfo(name = "team_b_name")
    private String teamBName;
    @ColumnInfo(name = "score_a")
    private int scoreTeamA;
    @ColumnInfo(name = "score_b")
    private int scoreTeamB;

    public Game(@NonNull String title, @NonNull String teamAName, @NonNull String teamBName, int scoreTeamA, int scoreTeamB) {
        this.title = title;
        this.teamAName = teamAName;
        this.teamBName = teamBName;
        this.scoreTeamA = scoreTeamA;
        this.scoreTeamB = scoreTeamB;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public int getScoreTeamA() {
        return scoreTeamA;
    }

    public void setScoreTeamA(int scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }

    public int getScoreTeamB() {
        return scoreTeamB;
    }

    public void setScoreTeamB(int scoreTeamB) {
        this.scoreTeamB = scoreTeamB;
    }
}
