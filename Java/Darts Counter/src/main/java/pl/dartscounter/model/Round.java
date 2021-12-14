package pl.dartscounter.model;

public class Round {

    private int throwNumber;

    public Round() {
        this.throwNumber = 0;
    }

    public void playerThrows() {
        if (throwNumber <= 3) {
            throwNumber++;
        }
    }

    public boolean isRoundContinuing() {
        return throwNumber < 3;
    }

    public void newRound() {
        throwNumber = 0;
    }

    public void endRound() {
        throwNumber = 3;
    }

    public int getThrowNumber() {
        return throwNumber;
    }
}
