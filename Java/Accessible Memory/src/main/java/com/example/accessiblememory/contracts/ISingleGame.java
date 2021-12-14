package com.example.accessiblememory.contracts;

public interface ISingleGame {

    interface View {
        void showCard(char c);
        void hideCard();
        void disableCard();
        void setCardId(int cardId, char currentCard);
        void openEndOfGameDialog();
        void showBoardSize(int sizeX, int sizeY);
        void vibrate();
    }

    interface Presenter {
        void moveLeft();
        void moveRight();
        void moveTop();
        void moveBottom();
        void chooseCard();
        void newGame();
        boolean isAlternativeNavigationEnabled();
    }
}
