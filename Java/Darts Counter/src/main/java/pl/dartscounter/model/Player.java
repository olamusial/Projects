package pl.dartscounter.model;

public class Player {

    private final int MAX_POINTS = 301;
    private int currentPoints;
    private int throwPoints[];
    private int throwIndex = 0;

    public Player() {
        this.currentPoints = MAX_POINTS;
        throwPoints = new int[3];
        resetPoints();
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public boolean isThrowValid(int value) {

        if (value > this.currentPoints)
            return false;
        else {
            currentPoints -= value;
            throwPoints[throwIndex++] = value;
            return true;
        }
    }

    public void resetPoints() {
        for (int i = 0; i < throwPoints.length; ++i) {
            throwPoints[i] = 0;
        }
        setThrowIndex(0);
    }

    public void undoRound() {
        for (int i = 0; i < throwIndex; ++i) {
            currentPoints += throwPoints[i];
        }
    }


    public boolean isWinner() {
        return this.currentPoints == 0;
    }

    public int getMAX_POINTS() {
        return MAX_POINTS;
    }

    public int getThrowPoints(int i) {
        return throwPoints[i-1];
    }

    public void setThrowPoints(int[] throwPoints) {
        this.throwPoints = throwPoints;
    }

    public int getThrowIndex() {
        return throwIndex;
    }

    public void setThrowIndex(int throwIndex) {
        this.throwIndex = throwIndex;
    }
}
