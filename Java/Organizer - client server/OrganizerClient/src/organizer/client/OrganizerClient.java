/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.client;

import java.io.IOException;

/**
 * The main class of the client
 *
 * @author Aleksandra Musiał
 * @version 1.0
 */
public class OrganizerClient {

    /**
     * Main method of the program - the user can send request to server to see
     * the tasks or to add a new one. The parameters for running the program can
     * be put in the command line or get from the user by the input stream
     *
     * @param args the command line arguments. Parameters in command line: 'r' +
     * ' ' + 'all/today/before/after' to read the tasks with the appropriate
     * filter (ex. "r all" to see all task) Parameters in command line: 'w' + '
     * ' + 'dd/MM/yyyy' + ' ' + '..nameOfTask' to write a new task with the date
     * in the presented format and with the name (ex. "w 20/12/2019
     * PaintingTheWalls" to add a new task) If the parameter with date is in
     * wrong format, the default date will be set for the current day
     */
    public static void main(String[] args) {

        try {
            ClientConnectionController client = new ClientConnectionController();
            try {
                if (args.length == 2 || args.length == 3) {
                    client.clientServiceWithParameters(args);
                } else {
                    client.clientService();
                }
            } catch (IOException e) {
                System.err.println("Problems with streams");
            }
        } catch (IOException e) {
            System.err.println("No connection with server");
        }
    }

}
