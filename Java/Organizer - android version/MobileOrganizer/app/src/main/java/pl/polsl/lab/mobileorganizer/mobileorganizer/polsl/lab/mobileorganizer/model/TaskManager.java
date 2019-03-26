package pl.polsl.lab.mobileorganizer.mobileorganizer.polsl.lab.mobileorganizer.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import pl.polsl.lab.mobileorganizer.mobileorganizer.pols.lab.mobileorganizer.exceptions.DatePriorTodayException;

/**
 * Class representing a manager of the tasks
 *
 * @author Aleksandra MusiaÅ‚
 * @version 2.0
 */
public class TaskManager {

    /**
     * list of tasks
     */
    private ArrayList<Task> taskList;

    /**
     * constructor
     */
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds new task to the list of tasks and sort the list by the date of the
     * tasks
     *
     * @param task to be added to the list
     * @throws DatePriorTodayException if date entered by user is before current
     * day
     */
    public void addTask(Task task) throws DatePriorTodayException {
        Date todayDate = new Date();

        if (task.getDate().compareTo(todayDate) < 0 && !(task.getDateString().equals(task.getDateFormat().format(todayDate)))) {
            throw new DatePriorTodayException("!The date you entered has already passed!\n");
        } else {
            taskList.add(task);
            Collections.sort(taskList);
        }

    }

    /**
     * Adds new task to the list of tasks and sort the list by the date of the
     * tasks
     *
     * @param date date of task to be added
     * @param name name of task to be added
     * @throws DatePriorTodayException if date entered by user is before current
     * day
     */
    public void addTask(String date, String name) throws DatePriorTodayException {
        Date todayDate = new Date();
        Task task = new Task();
        task.setDate(date);
        task.setName(name);
        if (task.getDate().compareTo(todayDate) < 0 && !(task.getDateString().equals(task.getDateFormat().format(todayDate)))) {
            throw new DatePriorTodayException("!The date you entered has already passed!\n");
        } else {
            taskList.add(task);
            Collections.sort(taskList);
        }

    }

    /**
     * Allows to get the whole sorted list of tasks
     *
     * @return list of existing tasks
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Allows to get the list of tasks which date is set for current day
     *
     * @return list of tasks for current day
     */
    public ArrayList<Task> getTaskListForToday() {
        ArrayList<Task> taskListFotToday = new ArrayList<>();

        if (!taskList.isEmpty()) {
            DateFormat dateFormat;
            dateFormat = taskList.get(0).getDateFormat();
            Date todayDate = new Date();

            for (Task t : taskList) {

                if (t.getDateString().equals(dateFormat.format(todayDate))) {
                    taskListFotToday.add(t);
                }
            }
        }

        return taskListFotToday;
    }

    /**
     * Allows to get the sorted list of tasks which date is set after current
     * day
     *
     * @return list of tasks for days after current day
     */
    public ArrayList<Task> getTaskListAfterToday() {
        ArrayList<Task> taskListFotToday = new ArrayList<>();

        if (!taskList.isEmpty()) {
            Date todayDate = new Date();

            for (Task t : taskList) {

                if (t.getDate().compareTo(todayDate) > 0) {
                    taskListFotToday.add(t);
                }
            }
        }

        return taskListFotToday;
    }

    /**
     * Allows to get the sorted list of tasks which date is set before current
     * day
     *
     * @return list of tasks for days before current day
     */
    public ArrayList<Task> getTaskListBeforeToday() {
        ArrayList<Task> taskListFotToday = new ArrayList<>();
        DateFormat dateFormat;

        if (!taskList.isEmpty()) {
            dateFormat = taskList.get(0).getDateFormat();
            Date todayDate = new Date();

            for (Task t : taskList) {
                if (t.getDate().compareTo(todayDate) < 0 && !(t.getDateString().equals(dateFormat.format(todayDate)))) {
                    taskListFotToday.add(t);
                }
            }
        }
        return taskListFotToday;
    }
}
