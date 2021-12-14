package pl.polsl.lab.mobileorganizer.mobileorganizer.pols.lab.mobileorganizer.exceptions;



/**
 * Exception class for informing about wrong date
 *
 * @author Aleksandra MusiaÅ‚
 * @version 1.0
 */
public class DatePriorTodayException extends Exception {

    /**
     * constructor with no arguments
     */
    public DatePriorTodayException() {
    }

    /**
     * constructor
     *
     * @param message to inform about the exception
     */
    public DatePriorTodayException(String message) {
        super(message);
    }
}
