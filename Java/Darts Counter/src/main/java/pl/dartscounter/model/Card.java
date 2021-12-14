package pl.dartscounter.model;

import java.util.ArrayList;

public class Card {

    private String playerTitle;
    private String playerScore;
    private String firstThrow;
    private String secondThrow;
    private String thirdThrow;
    private ArrayList<String> availableScores;

    public String getPlayerScore() {
        return playerScore;
    }

    public Card() {
        availableScores = new ArrayList<>();
        prepareAvailableScores();
    }

    public Card(String playerTitle, String playerScore, String firstThrow, String secondThrow, String thirdThrow) {
        this.playerTitle = playerTitle;
        this.playerScore = playerScore;
        this.firstThrow = firstThrow;
        this.secondThrow = secondThrow;
        this.thirdThrow = thirdThrow;
        availableScores = new ArrayList<>();
        prepareAvailableScores();
    }

    public void prepareAvailableScores() {

        availableScores.add(String.valueOf(25));
        availableScores.add(String.valueOf(50));
        availableScores.add(String.valueOf(0));

        for (int i = 20; i > 0; --i) {
            for (int j = 1; j < 4; ++j) {
                availableScores.add(String.valueOf(i * j));
            }
        }


    }

    public String getPlayerTitle() {
        return playerTitle;
    }

    public void setPlayerTitle(String playerTitle) {
        this.playerTitle = playerTitle;
    }

    public void setPlayerScore(String playerScore) {
        this.playerScore = playerScore;
    }

    public ArrayList<String> getAvailableScores() {
        return availableScores;
    }

    public void setAvailableScores(ArrayList<String> availableScores) {
        this.availableScores = availableScores;
    }

    public String getFirstThrow() {
        return firstThrow;
    }

    public void setFirstThrow(String firstThrow) {
        this.firstThrow = firstThrow;
    }

    public String getSecondThrow() {
        return secondThrow;
    }

    public void setSecondThrow(String secondThrow) {
        this.secondThrow = secondThrow;
    }

    public String getThirdThrow() {
        return thirdThrow;
    }

    public void setThirdThrow(String thirdThrow) {
        this.thirdThrow = thirdThrow;
    }
}
