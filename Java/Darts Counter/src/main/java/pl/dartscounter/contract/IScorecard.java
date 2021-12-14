package pl.dartscounter.contract;

import java.util.ArrayList;

public interface IScorecard {

    interface View {
        void updateThrows(String firstThrow, String secondThrow, String thirdThrow);

        void updateThrowsButtons(int buttonIndex);

        void updatePlayerName(String name);

        void updateScore(String score);

        void winner(int playerId);

        void prepare(ArrayList<String> availableScores);

        void disableThrowsButtons();

        void clearThrows();

        void disableScoreButtons();

        void enableScoreButtons();
    }

    interface Presenter {
        void setPlayersNumber(int playersNumber);

        void nextPlayer();

        void previousPlayer();

        void playerThrows(int value);
    }
}
