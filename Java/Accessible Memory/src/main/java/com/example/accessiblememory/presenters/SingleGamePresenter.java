package com.example.accessiblememory.presenters;

import android.content.Context;
import android.content.res.Resources;
import android.view.WindowManager;

import com.example.accessiblememory.contracts.IGamePresenter;
import com.example.accessiblememory.contracts.ISingleGame;
import com.example.accessiblememory.models.Board;
import com.example.accessiblememory.models.Options;
import com.example.accessiblememory.models.SoundPoolPlayer;

public class SingleGamePresenter extends BasePresenter<ISingleGame.View> implements ISingleGame.Presenter, IGamePresenter {

    private Options options = Options.getInstance();
    private Board board;
    private WindowManager windowManager;
    private Resources resources;
    private SoundPoolPlayer soundPoolPlayer;
    private Context context;

    public SingleGamePresenter(WindowManager windowManager, Resources resources, Context context) {
        this.windowManager = windowManager;
        this.resources = resources;
        board = new Board(options.getDifficultyLevel().sizeY, options.getDifficultyLevel().sizeX);
        soundPoolPlayer = new SoundPoolPlayer(context);
        soundPoolPlayer.playSwipeSound();
    }

    @Override
    public void moveLeft() {
        moveOnHorizontally(-1);
    }

    @Override
    public void moveRight() {
        moveOnHorizontally(+1);
    }

    @Override
    public void moveTop() {
        moveOnVertically(-1);
    }

    @Override
    public void moveBottom() {
        moveOnVertically(+1);
    }

    private void moveOnHorizontally(int movement) {

        if (board.changePositionHorizontally(movement)) {
            view.setCardId(board.getCurrentPositionY() * board.getSizeY() + board.getCurrentPositionX() + 1, board.getCurrentCard());
            soundPoolPlayer.playSwipeSound();
            if (board.getCurrentCard() == ' ') {
                view.hideCard();
            } else {
                view.showCard(board.getCurrentCard());
                if(board.alreadyRevealed()) {
                    view.disableCard();
                }
            }
        }
        else {
            soundPoolPlayer.playEndOfBoardSound();
            if(options.isEnableVibration())
                view.vibrate();
        }
    }

    private void moveOnVertically(int movement) {
        if (board.changePositionVertically(movement)) {
            view.setCardId(board.getCurrentPositionY() * board.getSizeY() + board.getCurrentPositionX() + 1, board.getCurrentCard());
            soundPoolPlayer.playSwipeSound();
            if (board.getCurrentCard() == ' ') {
                view.hideCard();
            } else {
                view.showCard(board.getCurrentCard());
                if(board.alreadyRevealed()) {
                    view.disableCard();
                }
            }
        }
        else {
            soundPoolPlayer.playEndOfBoardSound();

            if(options.isEnableVibration())
                view.vibrate();
        }
    }

    @Override
    public void chooseCard() {

        switch (board.revealCard()) {

            case FIRST_CHOICE:
                soundPoolPlayer.playCardSound();
                view.showCard(board.getCurrentCard());
                break;
            case SECOND_CHOICE:
                soundPoolPlayer.playCardSound();
                view.showCard(board.getCurrentCard());
                if (board.isWholeBoardRevealed())
                    endOfGame();
                else if (board.isPairFounded())
                    soundPoolPlayer.playCorrectAnswerSound();
                else {
                    board.hideCurrentCards();
                    soundPoolPlayer.playIncorrectAnswerSound();
                }
                board.clearCurrentCards();
                break;
            case ALREADY_REVEALED:
                break;
        }
    }

    public void endOfGame() {
        soundPoolPlayer.playWinSound();
        view.showCard(board.getCurrentCard());
        view.openEndOfGameDialog();

    }

    @Override
    public void newGame() {
        board.clearCurrentCards();
        board.clearPlayersChoices();
        board.prepareCardsToGuess();
        view.hideCard();
        view.showBoardSize(options.getDifficultyLevel().sizeX, options.getDifficultyLevel().sizeY);

        if(options.isSoundOn()) {
            soundPoolPlayer.unmute();
        } else {
            soundPoolPlayer.mute();
        }
    }

    @Override
    public boolean isAlternativeNavigationEnabled() {
        return this.options.isAlternativeNavigation();
    }
}
