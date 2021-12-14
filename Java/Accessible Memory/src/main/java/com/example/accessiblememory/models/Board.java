package com.example.accessiblememory.models;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;

public class Board {

    private int sizeX = 2;
    private int sizeY = 3;
    private char[][] cardsToGuess;
    private char[][] playersChoices;
    private ArrayList<Pair<Integer, Integer>> currentCardsPositions;
    private int currentPositionX = 0;
    private int currentPositionY = 0;

    public Board(int sizeX, int sizeY) {
        if ((sizeX * sizeY) % 2 == 0) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }

        cardsToGuess = new char[sizeX][sizeY];
        playersChoices = new char[sizeX][sizeY];
        currentCardsPositions = new ArrayList<>();

        clearPlayersChoices();
        prepareCardsToGuess();
    }


    public Board() {
        cardsToGuess = new char[sizeX][sizeY];
        playersChoices = new char[sizeX][sizeY];
        currentCardsPositions = new ArrayList<>();

        clearPlayersChoices();
        prepareCardsToGuess();

    }

    public int getCurrentPositionX() { return this.currentPositionX; }
    public int getCurrentPositionY() { return this.currentPositionY; }
    public int getSizeX() { return sizeX; }
    public int getSizeY() { return sizeY; }

    public void clearCurrentCards() {
        currentCardsPositions.clear();
    }

    public CardStatus revealCard() {
        if(playersChoices[currentPositionY][currentPositionX] == cardsToGuess[currentPositionY][currentPositionX])
            return CardStatus.ALREADY_REVEALED;
        else {
            playersChoices[currentPositionY][currentPositionX] = cardsToGuess[currentPositionY][currentPositionX];
            currentCardsPositions.add(new Pair<>(currentPositionY, currentPositionX));
            if(currentCardsPositions.size() == 1)
                return CardStatus.FIRST_CHOICE;
            else
                return CardStatus.SECOND_CHOICE;
        }
    }

    public boolean alreadyRevealed() {
        if(playersChoices[currentPositionY][currentPositionX] == cardsToGuess[currentPositionY][currentPositionX])
            return true;
        if(currentCardsPositions.size() == 1 && ( currentPositionX == currentCardsPositions.get(0).first && currentPositionY == currentCardsPositions.get(0).second)) {
            return true;
        }
        if(currentCardsPositions.size() == 2 && ( currentPositionX == currentCardsPositions.get(1).first && currentPositionY == currentCardsPositions.get(1).second)) {
            return true;
        }
        return false;
    }

    public void changeCurrentPosition(int id) {
        int i = (id - 1) / sizeX;
        int j = id - 1 - i * sizeX;
        this.currentPositionX = i ;
        this.currentPositionY = j;
    }

    public boolean changePositionVertically(int positionMovement) {
        if(currentPositionY + positionMovement < 0 || currentPositionY + positionMovement >= sizeX )
            return false;
        else
            this.currentPositionY += positionMovement;
        return true;
    }

    public boolean changePositionHorizontally(int positionMovement) {
        if(currentPositionX + positionMovement < 0 || currentPositionX + positionMovement >= sizeY )
            return false;
        else
            this.currentPositionX += positionMovement;
        return true;
    }

    public void clearPlayersChoices() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                playersChoices[i][j] = ' ';
            }
        }
        this.currentPositionX = 0;
        this.currentPositionY = 0;
    }

    public Pair<Integer, Integer> getPlayersChoicesIds() {
        return new Pair<>(currentCardsPositions.get(0).second * sizeX + currentCardsPositions.get(0).first + 1, currentCardsPositions.get(1).second * sizeX + currentCardsPositions.get(1).first + 1);
    }

    public void hideCurrentCards() {
        playersChoices[currentCardsPositions.get(0).first][currentCardsPositions.get(0).second] = ' ';
        playersChoices[currentCardsPositions.get(1).first][currentCardsPositions.get(1).second] = ' ';
    }

    public boolean isPairFounded() {
        if(cardsToGuess[currentCardsPositions.get(0).first][currentCardsPositions.get(0).second] ==
                cardsToGuess[currentCardsPositions.get(1).first][currentCardsPositions.get(1).second]) {
            return true;
        }
        else
            return false;
    }

    public boolean isWholeBoardRevealed() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if(playersChoices[i][j] != cardsToGuess[i][j])
                    return false;
            }
        }
        return true;
    }

    public char getCurrentCard() {
        return playersChoices[currentPositionY][currentPositionX];
    }

    public void prepareCardsToGuess() {
        ArrayList<Character> listOfCards = new ArrayList<>();
        int s = 0;

        for (int i = 0; i < sizeX * sizeY; i++) {
            if (i % 2 == 0)
                listOfCards.add((char) (s++ + 'A'));
            else
                listOfCards.add(listOfCards.get(i-1));
        }

        Collections.shuffle(listOfCards);

        int i = 0;
        for (int k = 0; k < cardsToGuess.length; k++) {
            for (int j = 0; j < cardsToGuess[k].length; j++) {
                cardsToGuess[k][j] = listOfCards.get(i++);
            }
        }
    }

}
