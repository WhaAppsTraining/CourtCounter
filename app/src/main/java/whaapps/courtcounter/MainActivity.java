package whaapps.courtcounter;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import whaapps.courtcounter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        // TODO datanya masih engga sinkron,
        // cari best practicenya realm dan update entity yang berupa POJO gimana
        // start observing changes from observable and react accordingly
        gameViewModel.getGameLiveData().observe(this, game -> {
            if (game != null) {
                activityMainBinding.tvTeamAScore.setText(String.valueOf(game.getScoreTeamA()));
                activityMainBinding.tvTeamBScore.setText(String.valueOf(game.getScoreTeamB()));
                activityMainBinding.tvGameTitle.setText(game.getTitle());
                activityMainBinding.tvTeamAName.setText(game.getTeamAName());
                activityMainBinding.tvTeamBName.setText(game.getTeamBName());
            }
        });

        // set click callbacks
        activityMainBinding.bTeamAPlus3.setOnClickListener(this);
        activityMainBinding.bTeamAPlus1.setOnClickListener(this);
        activityMainBinding.bTeamBPlus3.setOnClickListener(this);
        activityMainBinding.bTeamBPlus1.setOnClickListener(this);
        activityMainBinding.bReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bTeamAPlus3:
                gameViewModel.addToTeamA(3);
                break;
            case R.id.bTeamAPlus1:
                gameViewModel.addToTeamA(1);
                break;
            case R.id.bTeamBPlus3:
                gameViewModel.addToTeamB(3);
                break;
            case R.id.bTeamBPlus1:
                gameViewModel.addToTeamB(1);
                break;
            case R.id.bReset:
                gameViewModel.resetScores();
                break;
        }
    }
}
