/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.view;

import java.util.ArrayList;

/** The view class which is resposible for displaying information for user
 *
 * @author Aleksandra Musiał
 * @version 1.0
 */
public class ClientView {

/**
 * Prints tasks which are in the ArrayList given as the parameter
 * 
 * @param list contains tasks to be printed
 */
    public void printTasksFromServer(ArrayList<String> list) {
        if(list.isEmpty())
            System.out.println("No tasks");
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * Prints message from server
     * 
     * @param message message from server
     */
   public void printServerMessage(String message){
        System.out.println(message);
    }

}