package com.example.accessiblememory.contracts;

import android.widget.Button;

public interface IGame {

    interface View {
        void prepareBoard(int sizeX, int sizeY, int buttonWidth, int buttonHeight);
        void showCard(char c, Button button);
        void hideCards();
        void disableFoundPair(int id1, int id2);
        void disableAllCards();
        void enableAllCards();
        void openEndOfGameDialog();
    }

    interface Presenter {
        void newGame();
        void chooseCard(int id, Button button);
    }
}
