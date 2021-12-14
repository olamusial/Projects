package com.example.accessiblememory.views;

import androidx.appcompat.app.AppCompatActivity;
import com.example.accessiblememory.R;
import com.example.accessiblememory.contracts.ILevel;
import com.example.accessiblememory.models.DifficultyLevels;
import com.example.accessiblememory.models.Options;
import com.example.accessiblememory.presenters.LevelPresenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LevelActivity extends AppCompatActivity implements ILevel.View, View.OnClickListener {

    private LevelPresenter levelPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.backToMenuActionBar);

        levelPresenter = new LevelPresenter();
        levelPresenter.attach(this);

        Button veryEasyButton = findViewById(R.id.veryEasyButton);
        Button easyButton = findViewById(R.id.easyButton);
        Button mediumButton = findViewById(R.id.mediumButton);
        Button hardButton = findViewById(R.id.hardButton);
        Button proButton = findViewById(R.id.proButton);

        veryEasyButton.setOnClickListener(this);
        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);
        proButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.veryEasyButton:
                setDifficultyLevel(DifficultyLevels.VERY_EASY);
                break;
            case R.id.easyButton:
                setDifficultyLevel(DifficultyLevels.EASY);
                break;
            case R.id.mediumButton:
                setDifficultyLevel(DifficultyLevels.MEDIUM);
                break;
            case R.id.hardButton:
                setDifficultyLevel(DifficultyLevels.HARD);
                break;
            case R.id.proButton:
                setDifficultyLevel(DifficultyLevels.PROFESSIONAL);
                break;
        }

        if(Options.getInstance().isSingleCardMode()) {
            startActivity(new Intent(LevelActivity.this, SingleGameActivity.class));
        } else {
            startActivity(new Intent(LevelActivity.this, GameActivity.class));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(LevelActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    private void setDifficultyLevel(DifficultyLevels level) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                getString(R.string.preferenceFileKey), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.preferenceDifficultyLevel), level.ordinal());
        editor.apply();

        levelPresenter.setDifficultyLevel(level);
    }
}
