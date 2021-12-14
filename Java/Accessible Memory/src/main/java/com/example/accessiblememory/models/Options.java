package com.example.accessiblememory.models;

public class Options {

    private static Options INSTANCE;

    private Options() { }

    public static Options getInstance() {
        if (INSTANCE == null)
            synchronized (Options.class) {
                if (INSTANCE == null)
                    INSTANCE = new Options();
            }
        return INSTANCE;
    }

    private DifficultyLevels difficultyLevel = DifficultyLevels.EASY; // default settings
    private boolean singleCardMode = false; // default settings
    private boolean enableVibration = true; // default settings
    private boolean alternativeNavigation = false; // default setting

    private boolean soundOn = true; // default setting

    public boolean isEnableVibration() {
        return enableVibration;
    }

    public void setEnableVibration(boolean enableVibration) {
        this.enableVibration = enableVibration;
    }

    public boolean isSingleCardMode() {
        return singleCardMode;
    }

    public void setSingleCardMode(boolean singleCardMode) {
        this.singleCardMode = singleCardMode;
    }

    public DifficultyLevels getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevels difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public boolean isAlternativeNavigation() {
        return alternativeNavigation;
    }

    public void setAlternativeNavigation(boolean alternativeNavigation) {
        this.alternativeNavigation = alternativeNavigation;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }
}
