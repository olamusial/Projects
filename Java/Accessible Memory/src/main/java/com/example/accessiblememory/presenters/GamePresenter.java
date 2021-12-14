package com.example.accessiblememory.presenters;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Button;

import com.example.accessiblememory.R;
import com.example.accessiblememory.contracts.IGame;
import com.example.accessiblememory.contracts.IGamePresenter;
import com.example.accessiblememory.models.Board;
import com.example.accessiblememory.models.Options;
import com.example.accessiblememory.models.SoundPoolPlayer;

public class GamePresenter extends BasePresenter<IGame.View> implements IGame.Presenter, IGamePresenter {

    private Options options = Options.getInstance();
    private Board board;

    private WindowManager windowManager;
    private Resources resources;
    private int actionBarHeight = 0;

    private SoundPoolPlayer soundPoolPlayer;

    public GamePresenter(WindowManager windowManager, Resources resources, Context context, int actionBarHeight) {
        this.windowManager = windowManager;
        this.resources = resources;
        this.actionBarHeight = actionBarHeight;
        board = new Board(options.getDifficultyLevel().sizeX, options.getDifficultyLevel().sizeY);
        soundPoolPlayer = new SoundPoolPlayer(context);
    }

    @Override
    public void newGame() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        int screenHeight = displayMetrics.heightPixels - actionBarHeight;
        int screenWidth = displayMetrics.widthPixels;
        int sizeX = options.getDifficultyLevel().sizeX;
        int sizeY = options.getDifficultyLevel().sizeY;

        screenWidth = screenWidth/sizeX - (2 + sizeX -1) * (int) resources.getDimension(R.dimen.buttonsMargin);
        screenHeight = screenHeight/sizeY - (2 + sizeX -1) * (int) resources.getDimension(R.dimen.buttonsMargin);

        board.clearCurrentCards();
        board.clearPlayersChoices();
        board.prepareCardsToGuess();

        this.view.prepareBoard(sizeX, sizeY, screenWidth, screenHeight);
        this.view.enableAllCards();
        this.view.hideCards();

        if(options.isSoundOn()) {
            soundPoolPlayer.unmute();
        } else {
            soundPoolPlayer.mute();
        }

    }

    @Override
    public void chooseCard(int id, Button button) {

        board.changeCurrentPosition(id);

        switch (board.revealCard()) {

            case FIRST_CHOICE:
                view.hideCards();
                view.showCard(board.getCurrentCard(), button);
                soundPoolPlayer.playCardSound();
                break;

            case SECOND_CHOICE:
                view.showCard(board.getCurrentCard(), button);

                if (board.isWholeBoardRevealed())
                    endOfGame(button);
                else if (board.isPairFounded()) {
                    view.disableFoundPair(board.getPlayersChoicesIds().first, board.getPlayersChoicesIds().second);
                    soundPoolPlayer.playCorrectAnswerSound();
                } else {
                    board.hideCurrentCards();
                    soundPoolPlayer.playIncorrectAnswerSound();
                }
                board.clearCurrentCards();
                break;

            case ALREADY_REVEALED:
                break;
        }
    }


    public void endOfGame(Button button) {
        soundPoolPlayer.playWinSound();
        view.showCard(board.getCurrentCard(), button);
        view.disableAllCards();
        view.openEndOfGameDialog();
    }
}
