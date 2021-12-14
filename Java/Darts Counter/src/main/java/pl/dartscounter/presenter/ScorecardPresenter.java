package pl.dartscounter.presenter;

import java.util.ArrayList;

import pl.dartscounter.contract.IScorecard;
import pl.dartscounter.model.Card;
import pl.dartscounter.model.Player;
import pl.dartscounter.model.Round;

public class ScorecardPresenter extends BasePresenter<IScorecard.View> implements IScorecard.Presenter {

    private int playersNumber;
    private Player[] players;
    private Player currentPlayer;
    private Card card;
    private Round round;
    private ArrayList<Integer> winners;
    private int currentPlayerIndex = 0;
    private boolean isRoundEnable = true;

    public ScorecardPresenter() {
        round = new Round();
        card = new Card();
    }

    @Override
    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
        players = new Player[this.playersNumber];
        winners = new ArrayList<>();

        for (int i = 0; i < this.playersNumber; i++)
            players[i] = new Player();

        currentPlayer = players[0];

        view.disableThrowsButtons();
        view.prepare(card.getAvailableScores());
        newRound();
       // updateView();
    }

    public void newRound() {
        view.updatePlayerName(String.valueOf(currentPlayerIndex + 1));
        view.clearThrows();
        view.updateScore(String.valueOf(currentPlayer.getCurrentPoints()));

        if(checkIfWinner()) {
            if(isRoundEnable) {
                view.disableScoreButtons();
                isRoundEnable = false;
            }

            String name = String.valueOf(currentPlayerIndex + 1) + " winner nr " +
                    String.valueOf(winners.indexOf(currentPlayerIndex) + 1);
            view.updatePlayerName(name);
            return;
        }
        if(!isRoundEnable) {
            view.enableScoreButtons();
            isRoundEnable = true;
        }
    }

    public void updateView() {
        view.updatePlayerName(String.valueOf(currentPlayerIndex + 1));
        view.updateThrowsButtons(currentPlayer.getThrowIndex());
        view.updateThrows(String.valueOf(currentPlayer.getThrowPoints(1)), String.valueOf(currentPlayer.getThrowPoints(2)), String.valueOf(currentPlayer.getThrowPoints(3)));
        view.updateScore(String.valueOf(currentPlayer.getCurrentPoints()));
    }


    @Override
    public void nextPlayer() {
        round.newRound();
        if (currentPlayerIndex < playersNumber - 1)
            currentPlayer = players[++currentPlayerIndex];
        else {
            currentPlayerIndex = 0;
            currentPlayer = players[currentPlayerIndex];
        }
        currentPlayer.resetPoints();
        newRound();
        view.updateThrowsButtons(currentPlayer.getThrowIndex());
        //updateView();
    }

    @Override
    public void previousPlayer() {
        round.newRound();
        if(currentPlayerIndex != 0) {
            currentPlayer = players[--currentPlayerIndex];
        }
        else {
            currentPlayerIndex = playersNumber - 1;
            currentPlayer = players[currentPlayerIndex];
        }
        currentPlayer.resetPoints();
        newRound();
        view.updateThrowsButtons(currentPlayer.getThrowIndex());
       // updateView();
    }

    @Override
    public void playerThrows(int value) {
        round.playerThrows();
        if(currentPlayer.isThrowValid(value)) {
            if(currentPlayer.isWinner()) {
               isRoundEnable = false;
                view.disableScoreButtons();
                view.winner(currentPlayerIndex + 1);
                winners.add(currentPlayerIndex);

                round.endRound();
            }
        } else {
            currentPlayer.undoRound();
            round.endRound();
        }

        updateView();

        if(round.isRoundContinuing() && !isRoundEnable) {
            isRoundEnable = true;
            view.enableScoreButtons();
        } else
            if(!round.isRoundContinuing()){
            isRoundEnable = false;
            view.disableScoreButtons();
        }
    }

    private boolean checkIfWinner() {
        return winners.contains(currentPlayerIndex);
    }
}
