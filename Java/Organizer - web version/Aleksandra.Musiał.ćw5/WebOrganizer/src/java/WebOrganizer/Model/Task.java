/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebOrganizer.Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *Class representing a task, and implementing Comparable interface
 *
 * @author Aleksandra Musiał
 * @version 3.0
 */
public class Task implements Comparable<Task> {

    /** name of the task */
    private String name;
    /** date of the task */
    private Date date;
    /** date format used to define date of the task */
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    
    /**
     * Sets task name 
     * 
     * @param name to be set for task name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets date for the task if the format is correct
     * If format is not correct the default date is the current day
     * 
     * @param dateString date as string to be parse and set as a date for task
     */
    public void setDate(String dateString) {
        try {
            this.date = DATE_FORMAT.parse(dateString);
        } catch (ParseException ex) {
            this.date = new Date();
        }
    }

    /**
     * Allows to get the date of the task as string
     * 
     * @return date of the task as string
     */
    public String getDateString() {
        return DATE_FORMAT.format(date);
    }
    
    /**
     * Allows to get the date of the task
     * 
     * @return date of the task
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Allows to get the name of the task
     * 
     * @return name of the task
     */
    public String getName() {
        return name;
    }
    
    /**
     * Allows to get the format of the date used in task
     * 
     * @return date format used in task
     */
    public DateFormat getDateFormat() {
        return DATE_FORMAT;
    }
    
    /**
     * Mathod override from interface Comparable
     * Copmares dates of two tasks - this task and task from parameter
     * 
     * @param o the object to be compared
     * @return 0 if dates are equal, greater than 0 if date of task is after the date of argument, less than 0 if date of task is before the date of argument
     */
    @Override
    public int compareTo(Task o) {
        return getDate().compareTo(o.getDate());
    }
    

}