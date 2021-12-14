package com.example.accessiblememory.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.accessiblememory.R;
import com.example.accessiblememory.contracts.IOptions;
import com.example.accessiblememory.presenters.OptionsPresenter;

public class OptionsActivity extends AppCompatActivity implements IOptions.View {

    private OptionsPresenter optionsPresenter;
    private Switch boardSwitch;
    private Switch vibrationSwitch;
    private Switch alternativeNavigationSwitch;
    private Switch soundSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.backToMenuActionBar);

        optionsPresenter = new OptionsPresenter();
        optionsPresenter.attach(this);


        boardSwitch = findViewById(R.id.boardSwitch);
        boardSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                optionsPresenter.setSingleCardMode(isChecked);
            }
        });

        vibrationSwitch = findViewById(R.id.vibrationSwitch);
        vibrationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                optionsPresenter.setEnableVibration(isChecked);
            }
        });

        alternativeNavigationSwitch = findViewById(R.id.navigationSwitch);
        alternativeNavigationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                optionsPresenter.setAlternativeNavigation(isChecked);
            }
        });

        soundSwitch = findViewById(R.id.soundSwitch);
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                optionsPresenter.setSoundOn(isChecked);
            }
        });

        optionsPresenter.uploadSettings();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initializeSingleCardMode(boolean singleCardMode) {
        boardSwitch.setChecked(singleCardMode);
    }

    @Override
    public void initializeEnableVibration(boolean enableVibration) {
        vibrationSwitch.setChecked(enableVibration);
    }

    @Override
    public void initializeAlternativeNavigation(boolean alternativeNavigation) {
        alternativeNavigationSwitch.setChecked(alternativeNavigation);
    }

    @Override
    public void initializeSoundOn(boolean sound) {
        soundSwitch.setChecked(sound);
    }


    @Override
    public void enableSingleCardModeOptions(boolean enable) {
        vibrationSwitch.setEnabled(enable);
        alternativeNavigationSwitch.setEnabled(enable);
    }
}
