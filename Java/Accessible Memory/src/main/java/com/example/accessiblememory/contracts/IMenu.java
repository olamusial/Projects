package com.example.accessiblememory.contracts;

public interface IMenu {

    interface View {
        void startGame();
        void goToOptions();
        void showHelp();
    }

    interface Presenter {
        void handlePlayButton();
        void handleOptionsButton();
        void handleHelpButton();
    }
}
