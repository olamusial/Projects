/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebOrganizer.Exceptions;

/**
 * Exception class for informing about wrong date
 * 
 * @author Aleksandra Musiał
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