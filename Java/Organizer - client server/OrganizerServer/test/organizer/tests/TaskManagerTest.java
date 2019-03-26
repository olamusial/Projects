/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.tests;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import organizer.exceptions.DatePriorTodayException;
import organizer.model.TaskManager;
import organizer.model.Task;

/**
 * Class for testing methods from model class
 * 
 * @author Aleksandra Musiał
 * @version 1.0
 */
public class TaskManagerTest {

    TaskManager taskManager;

    @Before
    public void setUp() {
        taskManager = new TaskManager();

        Task taskBefore = new Task();
        Task taskToday = new Task();
        Task taskAfter = new Task();

        String today = taskToday.getDateFormat().format(new Date());
        taskBefore.setDate("10/10/2010");
        taskToday.setDate(today);
        taskAfter.setDate("12/12/2500");

        taskManager.getTaskList().add(taskBefore);
        taskManager.getTaskList().add(taskToday);
        taskManager.getTaskList().add(taskAfter);
    }

    @Test(expected = DatePriorTodayException.class)
    public void testAddTaskWithException() throws DatePriorTodayException {
        taskManager.addTask("01/01/2000","default");
    }

    @Test
    public void testAddTask() {
        int lenghtBeforeAdding = taskManager.getTaskList().size();
        try {
            taskManager.addTask("12/12/2500","default" );

        } catch (DatePriorTodayException ex) {
            fail("The exception shouldn't be thrown");
        }
        int lenghtAfterAdding = taskManager.getTaskList().size();
        if ((lenghtBeforeAdding + 1) != lenghtAfterAdding) {
            fail("The task was not added");
        }
    }

    @Test
    public void testAddTaskWithWrongDateFormat() {
        taskManager.getTaskList().clear();

        try {
            taskManager.addTask("12.12.2500","default");

        } catch (DatePriorTodayException ex) {
            fail("The exception shouldn't be thrown");
        }

        Task t = taskManager.getTaskList().get(taskManager.getTaskList().size() - 1);
        Date today = new Date();
        if (!t.getDateString().equals((t.getDateFormat()).format(today))) {
            fail("Date with the wrong format should be changet to today's date");
        }

    }

    @Test
    public void testGetTaskListForToday() {

        ArrayList<Task> todayList = taskManager.getTaskListForToday();

        for (Task t : todayList) {
            if (!t.getDateString().equals((t.getDateFormat()).format(new Date()))) {
                fail("There should be only tasks with today's date");
            }

        }

    }

    @Test
    public void testGetTaskListAfterToday() {

        ArrayList<Task> todayList = taskManager.getTaskListAfterToday();

        for (Task t : todayList) {
            if (t.getDate().compareTo(new Date()) <= 0) {
                fail("There should be only tasks with date after today");
            }

        }

    }

    @Test
    public void testGetTaskListBeforeToday() {

        ArrayList<Task> todayList = taskManager.getTaskListBeforeToday();

        for (Task t : todayList) {
            if (t.getDate().compareTo(new Date()) >= 0) {
                fail("There should be only tasks with date before today");
            }

        }

    }
    
    @Test
    public void testCompareTo() {
        
        Task taskBefore = new Task();
        Task taskAfter = new Task();

        taskBefore.setDate("10/10/2010");
        taskAfter.setDate("12/12/2500");
        
        if(taskBefore.compareTo(taskAfter) >= 0){
            fail("Wrong result of comparison of dates");
        }
    }
}