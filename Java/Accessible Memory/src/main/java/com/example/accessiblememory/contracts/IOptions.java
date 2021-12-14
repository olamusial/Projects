package com.example.accessiblememory.contracts;

public interface IOptions {

    interface View {
        void initializeSingleCardMode(boolean singleCardMode);
        void initializeEnableVibration(boolean enableVibration);
        void initializeAlternativeNavigation(boolean alternativeNavigation);
        void initializeSoundOn(boolean sound);
        void enableSingleCardModeOptions(boolean enable);
    }

    interface Presenter {
        void setSingleCardMode(boolean singleCardMode);
        void setEnableVibration(boolean enableVibration);
        void setAlternativeNavigation(boolean alternativeNavigation);
        void setSoundOn(boolean sound);
        void uploadSettings();
    }
}
