package com.example.accessiblememory.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.accessiblememory.R;
import com.example.accessiblememory.contracts.IMenu;
import com.example.accessiblememory.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements IMenu.View, View.OnClickListener {

    private MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter();
        mainPresenter.attach(this);

        Button playButton = findViewById(R.id.playButton);
        Button optionsButton = findViewById(R.id.optionsButton);
        Button helpButton = findViewById(R.id.helpButton);

        playButton.setOnClickListener(this);
        optionsButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                mainPresenter.handlePlayButton();
                break;
            case R.id.optionsButton:
                mainPresenter.handleOptionsButton();
                break;
            case R.id.helpButton:
                mainPresenter.handleHelpButton();
                break;
        }
    }

    @Override
    public void startGame() {
        startActivity(new Intent(MainActivity.this, LevelActivity.class));
    }

    @Override
    public void goToOptions() {
        startActivity(new Intent(MainActivity.this, OptionsActivity.class));
    }

    @Override
    public void showHelp() {
        startActivity(new Intent(MainActivity.this, HelpActivity.class));
    }


}
